package cn.bmob.ad.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.ad.Config;
import cn.bmob.ad.R;
import cn.bmob.ad.adapter.FeedAdapter;
import cn.bmob.ad.base.BaseFragment;
import cn.bmob.ad.bean.Feed;
import cn.bmob.ad.util.FeedsLog;
import cn.bmob.ad.widget.FeedsAbsListView;
import cn.bmob.ad.widget.FeedsListView;
import cn.bmob.ad.widget.FeedsWebView;
import cn.bmob.ad.widget.SimpleFooter;
import cn.bmob.ad.widget.SimpleHeader;

/**
 * Created on 17/11/14 10:33
 *
 * @author zhangchaozhou
 */

public class ContentFragment extends BaseFragment implements View.OnClickListener {


    private FeedsListView mLvContent;
    private FeedsWebView mFeedsWebView;
    private FeedAdapter mFeedAdapter;
    private List<Feed> mFeeds = new ArrayList<>();
    private String mTitle;
    private TextView mTvEmpty;

    private String mBfId = "";
    private String mBaId = "";
    private String mUrl = "";
    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_content, null, false);
        initView(view);
        initListener();
        return view;
    }


    /**
     *
     */
    private void initListener() {
        mTvEmpty.setOnClickListener(this);
        mLvContent.setOnRefreshStartListener(new FeedsListView.OnStartListener() {
            @Override
            public void onStart() {
                mFeeds.clear();
            }
        });
        mLvContent.setOnLoadMoreStartListener(new FeedsListView.OnStartListener() {
            @Override
            public void onStart() {
                if (mBfId.equals(Config.NONE)) {
                    mLvContent.setLoadMoreSuccess();
                } else {
                }
            }
        });
        // 外部对RecyclerView设置监听
        mLvContent.setOnScrollListener(new FeedsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(FeedsAbsListView view, int scrollState) {
                switch (scrollState) {

                    case SCROLL_STATE_IDLE:
                        //滑动停止
                        try {
                            if (getContext() != null) {
                                Glide.with(getContext()).resumeRequests();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SCROLL_STATE_FLING:
                        //正在滚动
                        try {
                            if (getContext() != null) {
                                Glide.with(getContext()).pauseRequests();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(FeedsAbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }


    /**
     * @param view
     */
    private void initView(View view) {
        mLinearLayout = view.findViewById(R.id.ll_container);
        mLvContent = view.findViewById(R.id.lv_content);
        mTvEmpty = view.findViewById(R.id.tv_empty);
        initFeedWebView();
        // 设置下拉刷新的样式
        SimpleHeader header = new SimpleHeader(getContext());
        header.setTextColor(0xff0066aa);
        header.setCircleColor(0xff33bbee);
        mLvContent.setHeadable(header);
        // 设置加载更多的样式
        SimpleFooter footer = new SimpleFooter(getContext());
        footer.setCircleColor(0xff33bbee);
        mLvContent.setFootable(footer);
        // 设置列表项出现动画
        mLvContent.setItemAnimForTopIn(R.anim.topitem_in);
        mLvContent.setItemAnimForBottomIn(R.anim.bottomitem_in);
    }


    /**
     *
     */
    private void initFeedWebView() {
        FeedsLog.e("初始化Webview");
        mFeedsWebView = new FeedsWebView(mContext);
        //回收问题：注意设置不能放在初始化中，需要放在新建对象下
        mLinearLayout.addView(mFeedsWebView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    @Override
    public void initData() {
        //判断类型
        data();
    }

    private void data() {

        mTitle = (String) getArguments().get(Config.INFO_TITLE);
        mUrl = (String) getArguments().get(Config.INFO_URL);
        if (TextUtils.isEmpty(mUrl)) {
            mFeedsWebView.setVisibility(View.GONE);
            mLvContent.setVisibility(View.VISIBLE);
        } else {
            mTvEmpty.setVisibility(View.GONE);
            mLvContent.setVisibility(View.GONE);
            mFeedsWebView.setVisibility(View.VISIBLE);
            new InitUserTask().execute();
        }
    }



    /**
     * @param feeds
     */
    public void addFeeds(List<Feed> feeds) {


        if (mFeeds.size() == 0) {
            mLvContent.setRefreshSuccess();
        } else {
            mLvContent.setLoadMoreSuccess();
            if (feeds == null || feeds.size() == 0 || mBfId.equals(Config.NONE)) {
                mLvContent.stopLoadMore();
            }
        }
        if (feeds != null) {
            mFeeds.addAll(feeds);
        }
        if (mFeedAdapter == null) {
            mFeedAdapter = new FeedAdapter(getContext(), mFeeds);
            mLvContent.setAdapter(mFeedAdapter);

        } else {
            mFeedAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        mFeeds.clear();
    }

    class InitUserTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mFeedsWebView.loadUrl(mUrl);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
            }catch(Exception e){
                //TODO :handle exception
                FeedsLog.i("init user failed. Exception:"+e.getMessage());
            }
            return null;
        }
    }

    @Override
    public void onDestroy() {

        FeedsLog.e("onDestroy");
        if (mFeedsWebView != null) {
            mLinearLayout.removeView(mFeedsWebView);
            mFeedsWebView.destroy();
        }
        super.onDestroy();
    }


    @Override
    protected void onInvisible() {
        if (mFeedsWebView != null) {
            mLinearLayout.removeView(mFeedsWebView);
            mFeedsWebView.destroy();
        }
        super.onInvisible();
    }


    @Override
    protected void onVisible() {
        super.onVisible();
        if (!TextUtils.isEmpty(mUrl)) {
            initFeedWebView();
            mTvEmpty.setVisibility(View.GONE);
            mLvContent.setVisibility(View.GONE);
            mFeedsWebView.setVisibility(View.VISIBLE);
            new InitUserTask().execute();
        }
    }


    /**
     *
     * 当前是否需要返回webview的上一页
     * @return
     */
    public boolean canGoBack() {

        FeedsLog.e("调用 can go back ");
        if (mFeedsWebView != null) {
            FeedsLog.e("存在webview");
            return mFeedsWebView.canGoBack();
        } else {
            return false;
        }
    }

    /**
     *
     * 返回webview的上一页
     * @return
     */
    public void goBack() {
        FeedsLog.e("content go back");
        mFeedsWebView.goBack();
    }


}
