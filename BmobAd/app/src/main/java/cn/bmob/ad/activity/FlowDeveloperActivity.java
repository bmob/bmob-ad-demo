package cn.bmob.ad.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.ad.R;
import cn.bmob.ad.adapter.FlowDeveloperAdapter;
import cn.bmob.ad.bean.Act;
import cn.bmob.ad.bean.Item;
import cn.bmob.ad.widget.FeedsAbsListView;
import cn.bmob.ad.widget.FeedsListView;
import cn.bmob.ad.widget.SimpleFooter;
import cn.bmob.ad.widget.SimpleHeader;

/**
 * Created on 17/12/11 17:00
 *
 * @author zhangchaozhou
 */

public class FlowDeveloperActivity extends AppCompatActivity {

    @BindView(R.id.lv_content)
    FeedsListView mLvContent;
    private List<Item> mItems = new ArrayList<>();
    private FlowDeveloperAdapter mFlowDeveloperAdapter;
    private Context mContext;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_developer);
        ButterKnife.bind(this);

        mContext = this;
        initView();


        //刷新操作
        mLvContent.setOnRefreshStartListener(new FeedsListView.OnStartListener() {
            @Override
            public void onStart() {
                mItems.clear();
                getItems();
            }
        });
        //加载更多操作
        mLvContent.setOnLoadMoreStartListener(new FeedsListView.OnStartListener() {
            @Override
            public void onStart() {
                if (mItems.size() >= 60) {
                    //没有更多数据可以加载，这里设置为超过60条数据就没有更多数据，具体设置条件开发者自己定义
                    mLvContent.setLoadMoreSuccess();
                } else {
                    getItems();
                }
            }
        });
        // 外部列表设置监听，停止滑动的时候才进行加载图片，以防内存溢出导致应用崩溃
        mLvContent.setOnScrollListener(new FeedsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(FeedsAbsListView view, int scrollState) {
                switch (scrollState) {

                    case SCROLL_STATE_IDLE:
                        //滑动停止
                        try {
                            if (mContext != null) {
                                Glide.with(mContext).resumeRequests();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SCROLL_STATE_FLING:
                        //正在滚动
                        try {
                            if (mContext != null) {
                                Glide.with(mContext).pauseRequests();
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

        //第一次请求数据
        getItems();
    }

    /**
     * 初始化UI
     */
    private void initView() {
        // 设置下拉刷新的样式
        SimpleHeader header = new SimpleHeader(mContext);
        header.setTextColor(0xff0066aa);
        header.setCircleColor(0xff33bbee);
        mLvContent.setHeadable(header);
        // 设置加载更多的样式
        SimpleFooter footer = new SimpleFooter(mContext);
        footer.setCircleColor(0xff33bbee);
        mLvContent.setFootable(footer);
        // 设置动画
        mLvContent.setItemAnimForTopIn(R.anim.topitem_in);
        mLvContent.setItemAnimForBottomIn(R.anim.bottomitem_in);
    }

    /**
     * 请求数据
     */
    private void getItems() {
        if (mItems.size() == 0) {
            mLvContent.setRefreshSuccess();
        } else {
            mLvContent.setLoadMoreSuccess();
            if (mItems.size() >= 60) {
                //没有更多数据可以加载，这里设置为超过60条数据就没有更多数据，具体设置条件开发者自己定义
                mLvContent.stopLoadMore();
            }
        }

        final List<Item> items = new ArrayList<>();
        //加载更多出现的数据，在这里以for循环的假数据代替，具体数据需要开发者自己定义
        for (int i = 0; i < 9; i++) {
            Item item = new Item();
            item.setContent("开发者自己列表内容");
            items.add(item);
        }

        //开发者自己的数据获取之后嵌入一条信息流广告：
        Item item = new Item();
        Act act = new Act();
        act.setUrl("");
        item.setAct(act);
        //在广告获取结果出来后再添加开发者的数据，以防两条广告连续出现
        mItems.addAll(items);
        mItems.add(item);
        //无论获取广告成功还是失败都应该刷新列表数据
        refresh();
    }

    /**
     * 刷新列表数据
     */
    private void refresh() {
        if (mFlowDeveloperAdapter == null) {
            mFlowDeveloperAdapter = new FlowDeveloperAdapter(mContext, mItems);
            mLvContent.setAdapter(mFlowDeveloperAdapter);
        } else {
            mFlowDeveloperAdapter.notifyDataSetChanged();
        }
    }
}
