package cn.bmob.ad.type;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

import cn.bmob.ad.AdWebActivity;
import cn.bmob.ad.Config;
import cn.bmob.ad.R;

/**
 * Created on 17/11/27 10:28
 *
 * @author zhangchaozhou
 */

public class FeedsSplashView extends RelativeLayout {
    private Handler mHandler;
    private Runnable mRunnable;
    private TextView mTextView;
    private ImageView mImageView;
    private Context mContext;

    private Intent mIntent;


    private String mUrl;

    private WeakReference<Activity> mActivityWeakReference;


    private int mTime;

    private boolean mClicked=false;

    public FeedsSplashView(Context context) {
        super(context);
        mHandler = null;
        mRunnable = null;
        mContext = context;
        init(context);
    }


    public FeedsSplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHandler = null;
        mRunnable = null;
        mContext = context;
        init(context);
    }


    public FeedsSplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHandler = null;
        mRunnable = null;
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FeedsFlashView);
        int time = typedArray.getInteger(R.styleable.FeedsFlashView_countTime, 5);
        this.mTime = time;
        init(context);
    }


    /**
     * @param context
     */
    private void init(Context context) {
       View view =  inflate(context, R.layout.view_splash, this);
        mImageView = view.findViewById(R.id.image_content);
        mTextView = view.findViewById(R.id.time_button);
        mTextView.setText(mTime + " 跳过");
        mTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mClicked =true;
                mContext.startActivity(new Intent(mContext, AdWebActivity.class)
                        .putExtra(Config.INFO_URL, mUrl));
                ((Activity)mContext).finish();
            }
        });
        setVisibility(GONE);
    }


    public void setTargetIntent(Activity activity, Intent intent) {
        mIntent = intent;
        mActivityWeakReference = new WeakReference(activity);
    }


    /**
     * 加载广告图片
     */
    public void loadFeeds(String image,String url) {
        mUrl = url;
        if (mIntent == null) {
            throw new IllegalArgumentException("target intent not set");
        } else {

            if (!TextUtils.isEmpty(image)) {

                if (image.endsWith(Config.GIF_LOW) || image.endsWith(Config.GIF_UP)) {
                    Glide.with(getContext()).load(image).asGif().placeholder(R.mipmap.ic_launcher).into(mImageView);
                } else {
                    Glide.with(getContext()).load(image).asBitmap().placeholder(R.mipmap.ic_launcher).into(mImageView);
                }
                setVisibility(VISIBLE);

                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mTime--;
                        mTextView.setText(mTime + " 跳过");
                        if (mTime > 0) {
                            mHandler.postDelayed(mRunnable, 1000L);
                        } else {
                            start();
                        }
                    }
                };
                mHandler = new Handler();
                mHandler.postDelayed(mRunnable, 1000L);
            } else {
                setVisibility(GONE);
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mTime--;
                        if (mTime > 0) {
                            mHandler.postDelayed(mRunnable, 1000L);
                        } else {
                            start();
                        }
                    }
                };
                mHandler = new Handler();
                mHandler.postDelayed(mRunnable, 1000L);
            }


        }
    }


    /**
     *
     */
    public void destroy() {
        if (mImageView != null) {
            mImageView = null;
        }
    }


    /**
     * 启动页面
     */
    private void start() {
        if (mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
        if (!mClicked){
            Activity activity = mActivityWeakReference.get();
            activity.finish();
            activity.startActivity(mIntent);
        }
    }

    /**
     * 设置倒数时间
     *
     * @param time
     */
    public void setCountTime(int time) {
        mTime = time;
        mTextView.setText(mTime + " 跳过");
    }

}
