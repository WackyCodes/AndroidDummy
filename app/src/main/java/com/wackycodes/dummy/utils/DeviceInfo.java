package com.wackycodes.dummy.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.format.Formatter;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  11/03/22, 4:51 PM
 *  Check : https://linktr.ee/wackycodes
 *  ===========================================================
 *  File Name : DeviceInfo.java
 *  Description :
 *  ======================   Updates History    ========================
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks
 *  1.    -    Shailendra    -   11/3/2022   -   File Created
 *
 ******************************************************************************/

public class DeviceInfo {

    public static String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAdder = networkInterface.getInetAddresses(); enumIpAdder.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAdder.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i("IP_ADDRESS", " IP= "+ ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("IP_ADDRESS", ex.toString());
        }
        return null;
    }

    public static String getAppVersion( Context context ){
        String version = null;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo( context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getVersionCode( Context context ){
        int versionCode = -1;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo( context.getPackageName(), 0);
            versionCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

}
