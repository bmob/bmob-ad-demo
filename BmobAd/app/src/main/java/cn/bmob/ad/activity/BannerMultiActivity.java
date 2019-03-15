package cn.bmob.ad.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.ad.R;
import cn.bmob.ad.base.BaseActivity;
import cn.bmob.ad.type.banner.multi.FeedsBannerMultiView;

/**
 * Created on 17/11/29 10:37
 * @author zhangchaozhou
 */

public class BannerMultiActivity extends BaseActivity {
    @BindView(R.id.banner)
    FeedsBannerMultiView mFeedsBannerMultiView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_multi);
        ButterKnife.bind(this);
        List<String> image = new ArrayList<>();
        image.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545818085674&di=73a096b4908a264115d453ee21f71bf9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e25259a8c8f7a8012028a99fb154.jpg%402o.jpg");
        image.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545818085674&di=961e74b27356e30351ede94680ca2ae1&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01a52958009e2ea84a0e282bd0d86c.jpg%402o.jpg");

        List<String> urls = new ArrayList<>();
        urls.add("http://s.bmobpay.com/?sid=e3d0f4316602e322df99");
        urls.add("http://s.bmobpay.com/?sid=e3d0f4316602e322df99");
        //图片地址、广告地址
        mFeedsBannerMultiView.loadFeeds(image,urls);
    }

    @Override
    protected void onDestroy() {
        if (mFeedsBannerMultiView!=null){
            mFeedsBannerMultiView.destory();
        }
        super.onDestroy();
    }
}
