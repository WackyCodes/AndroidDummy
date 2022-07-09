package com.wackycodes.dummy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.wackycodes.dummy.R;
import com.wackycodes.dummy.databinding.FragmentDialogForPermissionBinding;
import com.wackycodes.dummy.listeners.OnPermissionListener;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  09/07/22, 12:30 PM
 *  Check : https://linktr.ee/wackycodes
 *  ===========================================================
 *  File Name : FragmentDialogForPermission.java
 *  Description :
 *  ======================   Updates History    ========================
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks
 *  1.    -    Shailendra    -   9/7/2022   -   File Created
 *
 ******************************************************************************/

public class FragmentDialogForPermission extends BottomSheetDialogFragment {
    private OnPermissionListener.OnPermissionDialogListener permissionDialogListener;
    private int permissionCode = -1;

    public FragmentDialogForPermission(OnPermissionListener.OnPermissionDialogListener permissionDialogListener, int permissionCode) {
        this.permissionDialogListener = permissionDialogListener;
        this.permissionCode = permissionCode;
    }

    private String title;
    private String description;
    private String textPositiveButton;
    private String textNegativeButton;


    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTextPositiveButton(String textPositiveButton) {
        this.textPositiveButton = textPositiveButton;
    }
    public void setTextNegativeButton(String textNegativeButton) {
        this.textNegativeButton = textNegativeButton;
    }

    private FragmentDialogForPermissionBinding permissionBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        permissionBinding = FragmentDialogForPermissionBinding.inflate( inflater, container, false );

        permissionBinding.setTitle(title);
        permissionBinding.setDescription(description);
        permissionBinding.setButtonTextPositive( textPositiveButton );
        permissionBinding.setButtonTextNegative( textNegativeButton );
//        permissionBinding.executePendingBindings();

//        this.setCancelable( false );

        onUIAction();
        return permissionBinding.getRoot();
    }

    private void onUIAction( ){
        permissionBinding.buttonClose.setOnClickListener( view -> {
            this.dismiss();
            permissionDialogListener.acceptLaterAction( permissionCode );
        });

        permissionBinding.tvBtnNegative.setOnClickListener( view -> {
            this.dismiss();
            permissionDialogListener.acceptLaterAction( permissionCode );
        });
        permissionBinding.tvBtnPositive.setOnClickListener( view -> {
            this.dismiss();
            permissionDialogListener.showPermissionDialog( permissionCode, true );
        });
    }


}