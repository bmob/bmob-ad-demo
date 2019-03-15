package cn.bmob.ad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.ad.MainActivity;
import cn.bmob.ad.R;
import cn.bmob.ad.base.BaseActivity;
import cn.bmob.ad.type.FeedsSplashView;

/**
 * Created on 17/11/28 15:38
 * @author zhangchaozhou
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.fsv)
    FeedsSplashView mFsv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mFsv.setTargetIntent(this,new Intent(mContext,MainActivity.class));
        mFsv.setCountTime(6);
        //图片地址、广告地址
        mFsv.loadFeeds("https://www.youmi.net/uploads/images/2018-04-27-17-10-37.jpg","http://s.bmobpay.com/?sid=e3d0f4316602e322df99");

    }


    @Override
    protected void onDestroy() {
        if (mFsv!=null){
            mFsv.destroy();
        }
        super.onDestroy();
    }
}
