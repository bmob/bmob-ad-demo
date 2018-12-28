package cn.bmob.ad.type;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import cn.bmob.ad.AdWebActivity;
import cn.bmob.ad.Config;
import cn.bmob.ad.R;

/**
 * Created on 17/11/30 15:40
 *
 * @author zhangchaozhou
 */

public class FeedsPush {
    private Context mContext;
    private NotificationManager mManager;
    private Integer mId = null;
    private CharSequence mContentTitle = null;
    private CharSequence mContentText = null;
    private Integer mSmallIcon = null;
    private Bitmap mLargeIcon = null;
    private CharSequence mSubText = null;
    private Boolean mAutoCancel = null;
    private Uri mSound;
    private PendingIntent mPendingIntent = null;
    private String mUrl;

    public FeedsPush(Context context) {
        mContext = context;
        mManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public PendingIntent getPendingIntent() {
        return mPendingIntent;
    }

    public FeedsPush setPendingIntent(PendingIntent pendingIntent) {
        mPendingIntent = pendingIntent;
        return this;
    }

    public Integer getId() {
        return mId;
    }

    public FeedsPush setId(Integer id) {
        mId = id;
        return this;
    }

    public CharSequence getContentTitle() {
        return mContentTitle;
    }

    public FeedsPush setContentTitle(CharSequence contentTitle) {
        mContentTitle = contentTitle;
        return this;
    }

    public CharSequence getContentText() {
        return mContentText;
    }

    public FeedsPush setContentText(CharSequence contentText) {
        mContentText = contentText;
        return this;
    }


    public Integer getSmallIcon() {
        return mSmallIcon;
    }

    public FeedsPush setSmallIcon(Integer smallIcon) {
        mSmallIcon = smallIcon;
        return this;
    }

    public Bitmap getLargeIcon() {
        return mLargeIcon;
    }

    public FeedsPush setLargeIcon(Bitmap largeIcon) {
        mLargeIcon = largeIcon;
        return this;
    }

    public CharSequence getSubText() {
        return mSubText;
    }

    public FeedsPush setSubText(CharSequence subText) {
        mSubText = subText;
        return this;
    }

    public Boolean getAutoCancel() {
        return mAutoCancel;
    }

    public FeedsPush setAutoCancel(Boolean autoCancel) {
        mAutoCancel = autoCancel;
        return this;
    }


    public Uri getSound() {
        return mSound;
    }

    public FeedsPush setSound(Uri sound) {
        mSound = sound;
        return this;
    }

    public void show() {
        if (getId() != null) {
            mManager.notify(getId(), notification());
        }
    }


    public Notification notification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        if (getContentTitle() != null) {
            builder.setContentTitle(getContentTitle());
        }
        if (getContentText() != null) {
            builder.setContentText(getContentText());
        }
        if (getSmallIcon() != null) {
            builder.setSmallIcon(getSmallIcon());
        }
        if (getLargeIcon() != null) {
            builder.setLargeIcon(getLargeIcon());
        }
        if (getSubText() != null) {
            builder.setSubText(getSubText());
        }

        if (getPendingIntent() != null) {
            builder.setContentIntent(getPendingIntent());
        }
        if (getAutoCancel() != null && getAutoCancel()) {
            builder.setAutoCancel(getAutoCancel());
        }
        if (getSound() != null) {
            builder.setSound(getSound());
        }


        String channelID = "Ad";

        String channelName = "Bmob";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }


        builder.setChannelId(channelID);


        return builder.build();
    }


    public void loadFeeds(String url) {
        setContentTitle("标题");
        setContentText("解释");
        setId(url.hashCode());
        Intent notificationIntent = new Intent(mContext, AdWebActivity.class).putExtra(Config.INFO_URL, url);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
        setPendingIntent(pendingIntent);
        setSmallIcon(R.mipmap.ic_launcher);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
        setLargeIcon(bitmap);
        show();
    }
}
