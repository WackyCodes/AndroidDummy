package com.wackycodes.dummy.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.wackycodes.dummy.R;
import com.wackycodes.dummy.databinding.ActivityShowFragmentBinding;
import com.wackycodes.dummy.listeners.FragmentListener;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  02/03/22
 *  Check : https://linktr.ee/wackycodes
 *  -------------------------------------------------------------------------------------------------
 *  File Name : ActivityShowFragment.java
 *  Description :  This class helps to show different types of fragments in the project.
 *  --------------------------  Updates History -----------------------------------------------------
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks --
 *  1.    -    Shailendra    -   2/3/2022   -   File Created
 *
 ******************************************************************************/

public class ActivityShowFragment extends BaseActivity implements FragmentListener {
    public static final int FRAGMENT_ABOUT_PROGRAM = 1;


    private ActivityShowFragmentBinding fragmentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_fragment);

        // Set Toolbar!!
        setToolBar( fragmentBinding.includeToolbar.toolbar, getString(R.string.app_name), true );

        // Show Fragment
//        setFrameLayout( new FragmentAbout() );

    }


    private void setFrameLayout(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(fragmentBinding.frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showNextFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentBinding.frameLayout.getId(), fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }



}