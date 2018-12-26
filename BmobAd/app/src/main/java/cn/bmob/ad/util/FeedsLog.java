package cn.bmob.ad.util;

import android.util.Log;

/**
 * Created on 17/11/20 15:30
 *
 * @author zhangchaozhou
 */

public class FeedsLog {
    private static final String TAG = FeedsLog.class.getSimpleName();
    /**
     * TODO 发布SDK的时候需要将DEBUG改为false
     */
    private static final boolean DEBUG = false;

    /**
     * @param msg
     */
    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }


    /**
     * @param msg
     */
    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    /**
     * @param msg
     */
    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    /**
     * @param msg
     */
    public static void v(String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }
    }

    /**
     * @param msg
     */
    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    /**
     * @param msg
     */
    public static void wtf(String msg) {
        if (DEBUG) {
            Log.wtf(TAG, msg);
        }
    }
}
