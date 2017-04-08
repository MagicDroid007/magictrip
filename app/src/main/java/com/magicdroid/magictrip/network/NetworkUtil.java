package com.magicdroid.magictrip.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * The Class NetworkUtil.
 */
public class NetworkUtil {
    /**
     * Checks if is internet connected.
     * @param context context of the calling class
     * @return true, if is internet connected
     */
    public static boolean isInternetConnected(Context context) {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

}
