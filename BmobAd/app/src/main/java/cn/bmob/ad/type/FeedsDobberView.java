package cn.bmob.ad.type;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.bmob.ad.AdWebActivity;
import cn.bmob.ad.Config;
import cn.bmob.ad.R;

/**
 * Created on 17/11/29 09:49
 *
 * @author zhangchaozhou
 */

public class FeedsDobberView extends RelativeLayout {


    private Context mContext;


    private ImageView mImageContentView;
    private ImageView mImageCloseView;

    private ImageView mImageFeedsView;

    private String mUrl;



    public FeedsDobberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedsDobberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context.getApplicationContext();

        if (getChildCount() == 0) {

            mImageContentView = new ImageView(context);
            mImageCloseView = new ImageView(context);
            mImageFeedsView = new ImageView(context);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);


            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView(mImageContentView, layoutParams);


            LayoutParams layoutParams1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams1.width = 50;
            layoutParams1.height = 50;
            layoutParams1.addRule(10);
            layoutParams1.addRule(11, -1);
            addView(mImageCloseView, layoutParams1);
            mImageCloseView.setImageResource(R.drawable.tm_close);


            LayoutParams layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams2.width = 50;
            layoutParams2.height = 50;
            layoutParams2.addRule(12);
            layoutParams2.addRule(11, -1);
            addView(mImageFeedsView, layoutParams2);
            mImageFeedsView.setImageResource(R.drawable.tm_ad_icon);


            mImageContentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, AdWebActivity.class)
                            .putExtra(Config.INFO_URL, mUrl));
                    setVisibility(GONE);
                }
            });

            mImageCloseView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setVisibility(GONE);
                }
            });


        }

    }

    /**
     *
     */
    public void destroy() {
        if (mImageContentView != null) {
            mImageContentView = null;
        }
    }

    public void loadFeeds(String image, String url) {
        mUrl = url;
        if (image.endsWith(Config.GIF_LOW) || image.endsWith(Config.GIF_LOW)) {
            Glide.with(mContext).load(image).asGif().diskCacheStrategy(DiskCacheStrategy.ALL).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageContentView);
        } else {
            Glide.with(mContext).load(image).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageContentView);
        }
    }
}
