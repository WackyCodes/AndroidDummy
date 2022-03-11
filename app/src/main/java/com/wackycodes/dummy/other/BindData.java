package com.wackycodes.dummy.other;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  11/03/22, 4:44 PM
 *  Check : https://linktr.ee/wackycodes
 *  ===========================================================
 *  File Name : BindData.java
 *  Description :
 *  ======================   Updates History    ========================
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks
 *  1.    -    Shailendra    -   11/3/2022   -   File Created
 *
 ******************************************************************************/

public class BindData {

    // Set View As Visible ( Show ) if Data is found!
    @BindingAdapter("app:showView")
    public static void showView(View view, @Nullable String data){
        if (view!=null){
            view.setVisibility( data!=null&&!data.equals("")? View.VISIBLE : View.GONE );
        }
    }

    // Set View As InVisible ( Hide ) if Data is found!
    @BindingAdapter("app:hideView")
    public static void hideView( View view,@Nullable String data){
        if (view!=null){
            view.setVisibility( data!=null&&!data.equals("")? View.GONE : View.VISIBLE );
        }
    }



}
