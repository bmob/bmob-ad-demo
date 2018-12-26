package cn.bmob.ad.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import cn.bmob.ad.Config;


/**
 * Created on 17/11/15 10:05
 *
 * @author zhangchaozhou
 */

public class AppUtils {
    /**
     * @param context
     * @return
     */
    public static String getBmobAppKey(Context context) {
        return getAppMetaData(context, Config.BMOB_APP_KEY);
    }


    public static String getBmobFeedsChannel(Context context) {
        return getAppMetaData(context, Config.BMOB_FEEDS_CHANNEL);
    }

    /**
     * @param ctx
     * @param key
     * @return
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }


    public static boolean checkApkExist(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
