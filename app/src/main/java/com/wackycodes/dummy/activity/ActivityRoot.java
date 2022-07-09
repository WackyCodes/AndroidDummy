package com.wackycodes.dummy.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.wackycodes.dummy.R;
import com.wackycodes.dummy.listeners.ActivityListener;
import com.wackycodes.dummy.listeners.OnPermissionListener;
import com.wackycodes.dummy.other.MenuOption;
import com.wackycodes.dummy.utils.InternetService;
import com.google.android.material.snackbar.Snackbar;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  02/03/22
 *  Check : https://linktr.ee/wackycodes
 *  -------------------------------------------------------------------------------------------------
 *  File Name : ActivityRoot.java
 *  Description :  This class is root class of all activity in the project.
 *  --------------------------  Updates History -----------------------------------------------------
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks --
 *  1.    -    Shailendra    -   2/3/2022   -   File Created
 *
 ******************************************************************************/

public class ActivityRoot extends AppCompatActivity
        implements InternetService.ConnectivityReceiverListener,
        ActivityListener, OnPermissionListener {
    private static final int REQUEST_PERMISSION_STORAGE = 100;
    private static final int REQUEST_PERMISSION_LOCATION = 101;
    private static final int REQUEST_PERMISSION_CAMERA = 102;
    private final InternetService connectionListener = new InternetService();

    private ProgressDialog progressDialog;
    private MenuOption menuOption = MenuOption.DEFAULT;

    public void showErrorLog(@Nullable String message) {
        Log.e("ERROR", ": " + message);
    }

    public void showInfoLog(@Nullable String message) {
        Log.i("INFO", ": " + message);
    }

    public void showDebugLog(@Nullable String message) {
        Log.d("DEBUG", "" + message);
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
            // TODO : Customise the Progress Dialog according to You!!
            progressDialog = new ProgressDialog( this );
            progressDialog.setTitle( "Please Wait..!");
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
    public void setPageTitle(@NonNull String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setSubTitle(@NonNull String subTitle) {
        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setSubtitle(subTitle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToHome() {
        finishAffinity();
    }

    public void setMenuOption(MenuOption menuOption) {
        this.menuOption = menuOption;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (menuOption) {
            case MENU_GO_HOME:
                // TODO : ADD Menu item for different Activity!!
//                getMenuInflater().inflate(R.menu.menu_for_home, menu);
                break;
            case MENU_SEARCH:
//                getMenuInflater().inflate(R.menu.menu_main, menu);
                break;
            case DEFAULT:
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        // TODO : Uncomment Below ( Action on Home Button Showing in toolbar )
//        else if (item.getItemId() == R.id.menu_item_home) {
//            goToHome();
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public void onPermissionGranted(int permissionCode) {
        // This is called only when permission is granted to the App!
        onPermissionGranted(permissionCode, true);
    }

    @Override
    public void onPermissionGranted(int PERMISSION_CODE, boolean isGrant) {
        // This is called if user denied permission or may granted!!
    }

    //---------- Check User Permission -------------------------------------------------------------
    @Override
    public boolean isStoragePermissionGranted() {
        //            ActivityCompat.requestPermissions(this, new String[]{
        //                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
        //                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_STORAGE);

        return ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public boolean isLocationPermissionGranted() {
        try {
            //                ActivityCompat.requestPermissions(this,
            //                        new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
            //                        REQUEST_PERMISSION_LOCATION
            //                );
            return ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestForPermission(int permissionCode) {
        switch (permissionCode) {
            case PERMISSION_CODE_STORAGE:
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_STORAGE);
                break;
            case PERMISSION_CODE_LOCATION:
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_PERMISSION_LOCATION
                );
                break;
            case PERMISSION_CODE_CAMERA:
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA
                );
                break;
            default:
                break;
        }
    }

    @Override
    public void requestForcePermission(int permissionCode) {
        String permission = "";
        switch (permissionCode) {
            case PERMISSION_CODE_STORAGE:
                permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                break;
            case PERMISSION_CODE_LOCATION:
                permission = Manifest.permission.ACCESS_COARSE_LOCATION;
                break;
            case PERMISSION_CODE_CAMERA:
                permission = Manifest.permission.CAMERA;
                break;
            default:
                permission = null;
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permission != null) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                requestForPermission(permissionCode);
            } else {
                // We passed true.. since there is no matched!
                showDebugLog("Permission Passed! Code = " + permissionCode);
//                onPermissionGranted( permissionCode, true );
                requestPermissionSetting();
            }
        } else {
            // We passed true.. since there is no matched!
            showDebugLog("Permission Passed! App is <= M :: Permission Code = " + permissionCode);
            onPermissionGranted(permissionCode, true);
        }
    }

    private void requestPermissionSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        this.startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch ( requestCode ){
            case REQUEST_PERMISSION_STORAGE:
                if ((grantResults.length >= 2
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                        ||
                        (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    onPermissionGranted(OnPermissionListener.PERMISSION_CODE_STORAGE);
                } else {
                    onPermissionGranted(OnPermissionListener.PERMISSION_CODE_STORAGE, false);
                }
                break;
            case REQUEST_PERMISSION_LOCATION:
                if ((grantResults.length >= 2
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                        ||
                        (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // permissionGranted!
                    onPermissionGranted(OnPermissionListener.PERMISSION_CODE_LOCATION);
                } else {
                    onPermissionGranted(OnPermissionListener.PERMISSION_CODE_LOCATION, false);
                }
                break;
            case REQUEST_PERMISSION_CAMERA:
                if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissionGranted!
                    onPermissionGranted(OnPermissionListener.PERMISSION_CODE_CAMERA);
                } else {
                    onPermissionGranted(OnPermissionListener.PERMISSION_CODE_CAMERA, false);
                }
                break;
            default:
//                int permissionCode = requestCode == REQUEST_PERMISSION_STORAGE ? PERMISSION_CODE_STORAGE :
//                        (requestCode == REQUEST_PERMISSION_LOCATION ? PERMISSION_CODE_LOCATION : -1);
//            showToast( getString( R.string.permission_denied ));
                onPermissionGranted(-1, false);
                break;

        }
    }

    // Show Or Hide Keyboard...
    private void showHideKeyBord(View view, boolean isHide) {
        if (isHide) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //Hide..!
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } else {
            //Show..!
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
