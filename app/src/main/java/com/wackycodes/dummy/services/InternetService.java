package com.wackycodes.dummy.services;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  02/03/22
 *  Check : https://linktr.ee/wackycodes
 *  -------------------------------------------------------------------------------------------------
 *  File Name : InternetService.java
 *  Description :  This class helps to check internet connection and update to you in the app.
 *  --------------------------  Updates History -----------------------------------------------------
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks --
 *  1.    -    Shailendra    -   2/3/2022   -   File Created
 *
 ******************************************************************************/

public class InternetService extends BroadcastReceiver {
    public static boolean isConnected = false;

    private ConnectivityReceiverListener connectionListener;

    public void setConnectionListener(ConnectivityReceiverListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        IS_INTERNET_ON = isConnected( context );
        Log.e("INTERNET", "onReceive!");
        if (connectionListener != null){
            isConnected = getConnectionState(context);
            connectionListener.onNetworkConnectionChanged( isConnected );
        }
    }

    private boolean getConnectionState( Context context ){
        if ( context == null ) return false;

        // register activity with the connectivity manager service
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Returns a Network object corresponding to
            // the currently active default data network.
            Network network = connectivityManager.getActiveNetwork();
            if (network == null ){
                return false;
            }
            // Representation of the capabilities of an active network.
            NetworkCapabilities activeNetwork = connectivityManager.getNetworkCapabilities( network );

            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            if ( activeNetwork.hasTransport( NetworkCapabilities.TRANSPORT_WIFI ) ){
                return true;
            }

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);

        } else {
            // if the android version is below M
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                android.net.NetworkInfo wifi = cm.getNetworkInfo( ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo mobile = cm.getNetworkInfo( ConnectivityManager.TYPE_MOBILE);
                return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
            } else
                return false;
        }
    }


    // To Use as Public Method! Or You can delete this
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo( ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo( ConnectivityManager.TYPE_MOBILE);
            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
            return false;
    }

    //--------------------
    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged( boolean isConnected );
    }


}
