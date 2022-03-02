package com.wackycodes.dummy.other;

import android.content.Context;
import android.content.SharedPreferences;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  02/03/22
 *  Check : https://linktr.ee/wackycodes
 *  -------------------------------------------------------------------------------------------------
 *  File Name : SharedPref.java
 *  Description :  This file is used to store local shared preference data
 *  --------------------------  Updates History -----------------------------------------------------
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks --
 *  1.    -    Shailendra    -   2/3/2022   -   File Created
 *
 ******************************************************************************/

public class SharedPref {
    private static final String SHARED_NAME = "WACKYCODES";

    public static final String SP_CODE_LANGUAGE = "language";

    public static final String KEY_CODE_USER_ID = "USER_ID";
    public static final String KEY_CODE_NAME = "NAME";
    public static final String KEY_CODE_MOBILE = "MOBILE";
    public static final String KEY_CODE_GENDER = "GENDER";
    public static final String KEY_CODE_PASSWORD = "PASSWORD";

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;


    public SharedPref( Context activity) {
        preferences = activity.getSharedPreferences( SHARED_NAME, Context.MODE_PRIVATE );
        editor = preferences.edit();
    }

    public static SharedPreferences getPreferences(Context activity){
        preferences = activity.getSharedPreferences( SHARED_NAME, Context.MODE_PRIVATE );
        return preferences;
    }

    public static SharedPreferences.Editor getEditor(Context activity ){
        editor = getPreferences(activity).edit();
        return editor;
    }

    public void clearPreference() {
        editor.clear();
        editor.commit();
    }

    //----------------------------------------------------------------------------------------------

    public static boolean isUserLogin( Context context ){
        // TODO : Add Your code!
        return false;
    }


}
