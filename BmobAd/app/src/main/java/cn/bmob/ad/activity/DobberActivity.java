package cn.bmob.ad.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.ad.R;
import cn.bmob.ad.base.BaseActivity;
import cn.bmob.ad.type.FeedsDobberView;

/**
 * Created on 17/11/29 09:48
 * @author zhangchaozhou
 */

public class DobberActivity extends BaseActivity {
    @BindView(R.id.fdv)
    FeedsDobberView mFdv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobber);
        ButterKnife.bind(this);
        //图片地址、广告地址
        mFdv.loadFeeds("https://www.youmi.net/uploads/images/2018-04-27-17-10-37.jpg","http://hd-site.lomark.cn/easytui/goldEgg-i/");

    }


    @Override
    protected void onDestroy() {
        if (mFdv!=null){
            mFdv.destroy();
        }
        super.onDestroy();
    }
}
