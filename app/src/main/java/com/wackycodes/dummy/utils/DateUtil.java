package com.wackycodes.dummy.utils;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  09/07/22, 1:03 PM
 *  Check : https://linktr.ee/wackycodes
 *  ===========================================================
 *  File Name : DateUtil.java
 *  Description :
 *  ======================   Updates History    ========================
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks
 *  1.    -    Shailendra    -   9/7/2022   -   File Created
 *
 ******************************************************************************/

public class DateUtil {

    public static String getCurrentDate(@Nullable String format ){
        format = format!=null? format : "dd/MM/yyyy";
        return new SimpleDateFormat( format, Locale.getDefault()).format(new Date());
    }


}
