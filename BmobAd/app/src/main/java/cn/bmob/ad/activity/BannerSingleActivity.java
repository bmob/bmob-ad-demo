package cn.bmob.ad.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.ad.R;
import cn.bmob.ad.type.banner.single.FeedsBannerSingleView;

/**
 * Created on 17/11/30 09:30
 *
 * @author zhangchaozhou
 */

public class BannerSingleActivity extends AppCompatActivity {

    @BindView(R.id.banner_top)
    FeedsBannerSingleView mBannerTop;
    @BindView(R.id.banner_tail)
    FeedsBannerSingleView mBannerTail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_single);
        ButterKnife.bind(this);

        //图片地址、广告地址
        mBannerTail.loadFeeds("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545818085674&di=73a096b4908a264115d453ee21f71bf9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e25259a8c8f7a8012028a99fb154.jpg%402o.jpg",
                "https://engine.easytui.com.cn/activities?appkey=8e60b3bebd65495caf3bfcd25e928125&adslotid=10691&mediatype=3");
        mBannerTop.loadFeeds("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545818085674&di=961e74b27356e30351ede94680ca2ae1&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01a52958009e2ea84a0e282bd0d86c.jpg%402o.jpg",
                "https://engine.easytui.com.cn/activities?appkey=8e60b3bebd65495caf3bfcd25e928125&adslotid=10691&mediatype=3");
    }

    @Override
    protected void onDestroy() {
        if (mBannerTop != null) {
            mBannerTop.destroy();
        }
        if (mBannerTail != null) {
            mBannerTail.destroy();
        }
        super.onDestroy();
    }
}
