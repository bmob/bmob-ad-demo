package cn.bmob.ad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.ad.activity.BannerMultiActivity;
import cn.bmob.ad.activity.BannerSingleActivity;
import cn.bmob.ad.activity.DobberActivity;
import cn.bmob.ad.activity.InterstitialActivity;
import cn.bmob.ad.activity.SplashActivity;
import cn.bmob.ad.base.BaseActivity;
import cn.bmob.ad.type.FeedsPush;

/**
 * Created on 17/11/29 09:11
 *
 * @author zhangchaozhou
 */

public class ExampleActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_splash, R.id.tv_interstitial, R.id.tv_banner_multi, R.id.tv_float,

            R.id.tv_push, R.id.tv_banner_single})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_push:
                FeedsPush feedsPush = new FeedsPush(mContext);
                //广告地址
                feedsPush.loadFeeds("http://hd-site.lomark.cn/easytui/goldEgg-i/");
                break;
            case R.id.tv_splash:
                startActivity(new Intent(mContext, SplashActivity.class));
                break;
            case R.id.tv_interstitial:
                startActivity(new Intent(mContext, InterstitialActivity.class));
                break;
            case R.id.tv_banner_multi:
                startActivity(new Intent(mContext, BannerMultiActivity.class));
                break;
            case R.id.tv_float:
                startActivity(new Intent(mContext, DobberActivity.class));
                break;
            case R.id.tv_banner_single:
                startActivity(new Intent(mContext, BannerSingleActivity.class));
                break;
            default:
                break;
        }
    }


}
