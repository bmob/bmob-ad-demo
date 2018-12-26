package cn.bmob.ad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import cn.bmob.ad.widget.FeedsWebView;

/**
 * Created on 2018/12/25 17:37
 *
 * @author zhangchaozhou
 */
public class AdWebActivity extends AppCompatActivity {


    private FeedsWebView mFeedsWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        mFeedsWebView = new FeedsWebView(this);

        addContentView(mFeedsWebView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mFeedsWebView.loadUrl("https://api.lomark.cn/landingview?cid=1005&idfa=__UDID__");

    }



}
