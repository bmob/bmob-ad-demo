package cn.bmob.ad.type.banner.single;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
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
import cn.bmob.ad.type.FeedsSize;

/**
 * Created on 17/11/29 10:24
 *
 * @author zhangchaozhou
 */

public class FeedsBannerSingleView extends RelativeLayout {
    private ImageView mImageContentView;
    private ImageView mImageCloseView;
    private ImageView mImageFeedsView;
    private Context mContext;
    private FeedsSize mFeedsSize;
    private String mUrl;


    public FeedsBannerSingleView(Context context, AttributeSet attrs) {
        //this, not super
        this(context, attrs, 0);
    }

    public FeedsBannerSingleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context.getApplicationContext();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FeedsBannerSingleView, defStyleAttr, 0);


        int size = typedArray.getInt(R.styleable.FeedsBannerSingleView_size, 1);
        switch (size) {
            case 0:
                mFeedsSize = FeedsSize.BANNER_640_280;
                break;
            case 1:
                mFeedsSize = FeedsSize.BANNER_640_150;
                break;
            default:
                mFeedsSize = FeedsSize.BANNER_640_280;
                break;
        }
        typedArray.recycle();


        if (getChildCount() == 0) {


            mImageContentView = new ImageView(context);
            mImageCloseView = new ImageView(context);
            mImageFeedsView = new ImageView(context);


            mImageContentView.setScaleType(ImageView.ScaleType.FIT_XY);


            DisplayMetrics displayMetrics = new DisplayMetrics();

            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);


            int height = displayMetrics.widthPixels * mFeedsSize.getHeight() / mFeedsSize.getWidth();

            setLayoutParams(new LayoutParams(displayMetrics.widthPixels, height));


            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            addView(mImageContentView, layoutParams);


            LayoutParams layoutParamsClose = new LayoutParams(-2, -2);
            layoutParamsClose.addRule(10);
            layoutParamsClose.addRule(11, -1);
            mImageCloseView.setImageResource(R.drawable.tm_close);
            addView(mImageCloseView, layoutParamsClose);
            mImageCloseView.setPadding(10, 10, 10, 10);
            LayoutParams layoutParamsFeeds = new LayoutParams(-2, -2);
            layoutParamsFeeds.addRule(12);
            layoutParamsFeeds.addRule(9);
            mImageFeedsView.setImageResource(R.drawable.tm_ad_icon);
            this.addView(mImageFeedsView, layoutParamsFeeds);


            mImageContentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, AdWebActivity.class)
                            .putExtra(Config.INFO_URL, mUrl).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int var3 = MeasureSpec.getSize(widthMeasureSpec);
        float var4 = (float) (var3 * this.mFeedsSize.getHeight() / this.mFeedsSize.getWidth());
        heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) var4, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        if (image.endsWith(Config.GIF_LOW) || image.endsWith(Config.GIF_UP)) {
            Glide.with(mContext).load(image).asGif().diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageContentView);
        } else {
            Glide.with(mContext).load(image).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageContentView);
        }
    }
}
