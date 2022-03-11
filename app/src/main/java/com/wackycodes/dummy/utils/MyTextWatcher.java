package com.wackycodes.dummy.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.wackycodes.dummy.R;

import java.util.Objects;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  11/03/22, 4:52 PM
 *  Check : https://linktr.ee/wackycodes
 *  ===========================================================
 *  File Name : MyTextWatcher.java
 *  Description :
 *  ======================   Updates History    ========================
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks
 *  1.    -    Shailendra    -   11/3/2022   -   File Created
 *
 ******************************************************************************/

public class MyTextWatcher implements TextWatcher {
    private TextInputLayout layout;
    private TextInputEditText editText;

    private String regex = null;
    private String errorMsg = null;

    public MyTextWatcher(TextInputLayout layout, TextInputEditText editText) {
        this.layout = layout;
        this.editText = editText;
    }

    public MyTextWatcher setFormat( String regex, String errorMsg ){
        this.regex = regex;
        this.errorMsg = errorMsg;
        return this;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        if (!charSequence.toString().isEmpty()){
//            layout.setErrorEnabled(false);
//        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!editable.toString().isEmpty()){
            if ( regex == null ){
                layout.setErrorEnabled(false);
            }else{
                if ( editable.toString().matches( regex )){
                    layout.setErrorEnabled(false);
                }else{
                    layout.setError( errorMsg!=null ? errorMsg : layout.getContext().getString(R.string.invalid_data) );
                }
            }
        }
    }

    public boolean isValidData( ){
        if (TextUtils.isEmpty( editText.getText() )){
            layout.setError( layout.getContext().getString(R.string.required_field) );
            layout.setErrorEnabled(true);
            return false;
        }
        if ( regex != null && !Objects.requireNonNull(editText.getText()).toString().matches( regex )){
            layout.setError( errorMsg!=null ? errorMsg : layout.getContext().getString(R.string.invalid_data) );
            layout.setErrorEnabled(true);
            return false;
        }
        return true;
    }

    public static boolean isEmpty( TextInputLayout layout, TextInputEditText editText ){
        if (TextUtils.isEmpty( editText.getText() )){
            layout.setError( layout.getContext().getString(R.string.required_field) );
            layout.setErrorEnabled(true);
            return true;
        }
        return false;
    }


}
