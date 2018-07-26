package com.cwl.imitatereadhub.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;

import com.cwl.imitatereadhub.MyApplication;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public class UtilsCode {


    /**
     * 判断是否有网
     * @return
     */
    public static boolean isConnect(){
        NetworkInfo info =getActiveNetWorkInfo();
        return info!=null&&info.isConnected();
    }
    @RequiresPermission(ACCESS_NETWORK_STATE)
    private static NetworkInfo getActiveNetWorkInfo(){
        ConnectivityManager connectivityManager= (ConnectivityManager) MyApplication.getmInstance().getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager==null){
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();

    }
}
