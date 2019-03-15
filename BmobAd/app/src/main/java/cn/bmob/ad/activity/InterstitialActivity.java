package cn.bmob.ad.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.bmob.ad.R;
import cn.bmob.ad.base.BaseActivity;
import cn.bmob.ad.type.FeedsInterstitialView;


/**
 * Created on 17/11/29 08:49
 */

public class InterstitialActivity extends BaseActivity {




    private FeedsInterstitialView mFeedsInterstitialView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        mFeedsInterstitialView  = new FeedsInterstitialView(this);
        //图片地址、广告地址
        mFeedsInterstitialView.loadFeeds("https://www.youmi.net/uploads/images/2018-04-27-17-10-37.jpg","http://s.bmobpay.com/?sid=e3d0f4316602e322df99");
    }

    @Override
    protected void onDestroy() {
        if (mFeedsInterstitialView!=null){
            mFeedsInterstitialView.destroy();
        }
        super.onDestroy();
    }
}
