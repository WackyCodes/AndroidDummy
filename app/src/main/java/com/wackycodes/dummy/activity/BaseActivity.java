package com.wackycodes.dummy.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.wackycodes.dummy.R;
import com.wackycodes.dummy.listeners.OnPermissionListener;
import com.wackycodes.dummy.listeners.RootListener;
import com.wackycodes.dummy.services.InternetService;
import com.google.android.material.snackbar.Snackbar;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  02/03/22
 *  Check : https://linktr.ee/wackycodes
 *  -------------------------------------------------------------------------------------------------
 *  File Name : BaseActivity.java
 *  Description :  This class is root class of all activity in the project.
 *  --------------------------  Updates History -----------------------------------------------------
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks --
 *  1.    -    Shailendra    -   2/3/2022   -   File Created
 *
 ******************************************************************************/

public class BaseActivity extends AppCompatActivity
        implements InternetService.ConnectivityReceiverListener,
        RootListener, OnPermissionListener {
    private static final int REQUEST_PERMISSION_STORAGE = 100;
    private static final int REQUEST_PERMISSION_LOCATION = 101;
    private final InternetService connectionListener = new InternetService();


    private ProgressDialog progressDialog;

    public void showErrorLog(@Nullable String message) {
        Log.e("ERROR", "" + message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectionListener, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        connectionListener.setConnectionListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(connectionListener);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void showToast(@Nullable String message) {
        if (message != null)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        else Log.e("showToast", "Message Not found!");
    }

    @Override
    public void showSnackMessage(@Nullable View view, @Nullable String message) {
        if (view == null) {
            Log.e("showSnackMessage", "view Not found!");
            return;
        }
        if (message != null)
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        else Log.e("showSnackMessage", "Message Not found!");
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getString(R.string.please_wait));
            progressDialog.setCancelable(false);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setToolBar(Toolbar toolbar, @Nullable String title, boolean isHomesUpEnabled) {
        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(title != null ? title : getString(R.string.app_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(isHomesUpEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPermissionGranted(int permissionCode) {

    }

    //---------- Check User Permission -------------------------------------------------------------
    public boolean isStoragePermissionGranted() {
        if (
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_STORAGE);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_STORAGE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted(OnPermissionListener.PERMISSION_CODE_STORAGE);
        }
        else if (requestCode == REQUEST_PERMISSION_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            // permissionGranted!
            onPermissionGranted(OnPermissionListener.PERMISSION_CODE_LOCATION);
        } else {
            showToast( getString( R.string.permission_denied ));
        }
    }

    public boolean isLocationPermissionGranted() {
        try {
            if ( ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                        REQUEST_PERMISSION_LOCATION
                );
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Show Or Hide Keyboard ...
    private void showHideKeyBord(View view, boolean isHide) {
        if (isHide) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //Hide..!
            imm.hideSoftInputFromWindow( view.getWindowToken(), 0 );
        } else {
            //Show..!
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }



}
