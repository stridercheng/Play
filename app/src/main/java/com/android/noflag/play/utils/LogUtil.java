package com.android.noflag.play.utils;

import com.android.noflag.play.MyApplication;

import android.util.Log;

/**
 * description:
 * User: stridercheng
 * Date: 2015-10-06
 * Time: 13:35
 * FIXME
 */
public class LogUtil {
    public static void e(String TAG, String message) {
        if (MyApplication.debugMode) {
            Log.e(TAG, message);
        }
    }
}
