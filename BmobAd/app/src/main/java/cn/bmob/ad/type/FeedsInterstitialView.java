package cn.bmob.ad.type;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.bmob.ad.AdWebActivity;
import cn.bmob.ad.Config;
import cn.bmob.ad.R;

/**
 * Created on 17/11/29 08:50
 *
 * @author zhangchaozhou
 */

public class FeedsInterstitialView {
    private Context mContext;
    private Dialog mDialog;
    private ImageView mImageView;
    private ImageButton mImageButton;

    private String mUrl;

    public FeedsInterstitialView(Context context) {
        mContext = context;
        mDialog = new Dialog(context, R.style.Theme_CustomDialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_interstitial, null, false);
        mDialog.setContentView(view);
        mImageView = view.findViewById(R.id.image_content);
        mImageButton = view.findViewById(R.id.close_button);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, AdWebActivity.class)
                        .putExtra(Config.INFO_URL, mUrl));
                mDialog.dismiss();
            }
        });

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.setCancelable(false);
        WindowManager.LayoutParams layoutParams = mDialog.getWindow().getAttributes();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        layoutParams.width = (int) (((double) displayMetrics.widthPixels) * 0.8D);
        layoutParams.height = (int) (((double) displayMetrics.heightPixels) * 0.8D);
        mDialog.getWindow().setAttributes(layoutParams);

    }


    public void destroy() {
        if (mImageView != null) {
            mImageView = null;
        }
    }

    public void loadFeeds(String image, String url) {
        mUrl = url;
        mDialog.show();
        if (image.endsWith(Config.GIF_LOW) || image.endsWith(Config.GIF_UP)) {
            Glide.with(mContext).load(image).asGif().diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
        } else {
            Glide.with(mContext).load(image).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
        }

    }
}
