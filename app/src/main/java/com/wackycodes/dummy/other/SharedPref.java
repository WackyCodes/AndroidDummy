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
    //----------------------------------------------------------------------------------------------

    public static void setUserData( Context context, Object student ){
        if (editor == null){
            editor = getEditor( context );
        }
//        editor.putString( KEY_CODE_USER_ID, student.getUserName() );
//        editor.putString( KEY_CODE_NAME, student.getStudentName() );
        editor.commit();
    }

    public static Object getUserData( Context context ){
        if (preferences == null){
            preferences = getPreferences( context );
        }
        Object student = new Object();
//        student.setUserName( preferences.getString( KEY_CODE_USER_ID,null) );
//        student.setStudentName( preferences.getString( KEY_CODE_NAME,null) );
        return student;
    }

    public static boolean isUserLogin( Context context ){
//        String info = getPreferences( context ).getString( KEY_CODE_USER_ID, null );
//        return !( info == null || info.isEmpty() );
        return false;
    }

    public static void logout( Context context ){
        if (editor == null){
            editor = getEditor( context );
        }
//        editor.putString( KEY_CODE_USER_ID, null );
        editor.clear();
        editor.commit();
    }

    //----------------------------------------------------------------------------------------------


}
