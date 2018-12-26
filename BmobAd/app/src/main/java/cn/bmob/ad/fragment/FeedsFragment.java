package cn.bmob.ad.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.ad.Config;
import cn.bmob.ad.R;
import cn.bmob.ad.base.BaseFragment;
import cn.bmob.ad.bean.Back;
import cn.bmob.ad.bean.Column;
import cn.bmob.ad.util.FeedsLog;
import cn.bmob.ad.widget.FeedsViewpager;

/**
 * Created on 17/11/14 09:22
 *
 * @author zhangchaozhou
 */

public class FeedsFragment extends BaseFragment implements View.OnClickListener {

    private HorizontalScrollView mHsvTitle;
    private FeedsViewpager mVpFragment;
    private TabFragmentPagerAdapter mAdapter;
    private List<Column> mTitles = new ArrayList<>();
    private TextView mTvError;
    private int mTabBackgroundColor = R.color.colorPrimary;
    private int mTabTextSelectedColor = Color.WHITE;
    private int mTabTextUnselectedColor = Color.LTGRAY;
    private int mTabTextSize = 18;

    private AppCompatDialogFragment mAppCompatDialogFragment;
    private int mCurrentPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_feeds, null, false);
        mHsvTitle = view.findViewById(R.id.hsv_title);
        mHsvTitle.setBackgroundColor(ContextCompat.getColor(getContext(), mTabBackgroundColor));
        mVpFragment = view.findViewById(R.id.vp_fragment);
        mTvError = view.findViewById(R.id.tv_error);
        mTvError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mAppCompatDialogFragment = new AppCompatDialogFragment();
        return view;
    }

    public void setTabBackgroundColor(int color) {
        mTabBackgroundColor = color;
        Config.mThemeColor = color;
    }


    public void setTabTextSelectedColor(int tabTextSelectedColor) {
        mTabTextSelectedColor = tabTextSelectedColor;
        Config.mThemeTextColor = tabTextSelectedColor;
    }

    public void setTabTextUnselectedColor(int tabTextUnselectedColor) {
        mTabTextUnselectedColor = tabTextUnselectedColor;
    }

    public void setTabTextSize(int tabTextSize) {
        mTabTextSize = tabTextSize;
        Config.mThemeSize = tabTextSize;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * @param back
     */
    private void initViews(Back back) {
        mTitles = back.getData().getColumns();

        initTitles();
        initViewPagerFragments();
    }

    /**
     * 初始话列表
     */
    private void initViewPagerFragments() {


        for (int i = 0; i < mTitles.size(); i++) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Config.INFO_TITLE, mTitles.get(i).getTitle());
            bundle.putString(Config.INFO_URL, mTitles.get(i).getUrl());
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }


        mAdapter = new TabFragmentPagerAdapter(getFragmentManager());
        mVpFragment.setOffscreenPageLimit(mTitles.size());
        mVpFragment.setAdapter(mAdapter);
        mVpFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setCurrentTab(int position) {
        for (int i = 0; i < mTvTitles.size(); i++) {
            mTvTitles.get(i).setTextColor(mTabTextUnselectedColor);
        }
        mTvTitles.get(position).setTextColor(mTabTextSelectedColor);
        if (mCurrentPosition == position) {
            //当前位置
        } else if (mCurrentPosition < position) {
            if (position > 2) {
                mHsvTitle.smoothScrollBy((int) getResources().getDimension(R.dimen.x50) * (position - mCurrentPosition), 0);
            }
        } else {
            mHsvTitle.smoothScrollBy(-(int) getResources().getDimension(R.dimen.x50) * (mCurrentPosition - position), 0);
        }
        mCurrentPosition = position;
    }

    private List<TextView> mTvTitles = new ArrayList<>();

    /**
     * 初始化导航栏的标题
     */
    private void initTitles() {
        mHsvTitle.removeAllViews();
        mTvTitles.clear();
        final LinearLayout layout = new LinearLayout(getContext());
        for (int i = 0; i < mTitles.size(); i++) {
            TextView tv = new TextView(getContext());
            mTvTitles.add(tv);
            tv.setId(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            if (i == 0) {
                tv.setTextColor(mTabTextSelectedColor);
            } else {
                tv.setTextColor(mTabTextUnselectedColor);
            }
            tv.setText(mTitles.get(i).getTitle());
            tv.setTextSize(mTabTextSize);
            tv.setPadding((int) getResources().getDimension(R.dimen.x8), (int) getResources().getDimension(R.dimen.x12), (int) getResources().getDimension(R.dimen.x8), (int) getResources().getDimension(R.dimen.x12));
            tv.setOnClickListener(this);
            layout.addView(tv, layoutParams);
        }
        mHsvTitle.addView(layout);
    }


    @Override
    public void onClick(View view) {
        if (mTitles.get(view.getId()).getType() == 0) {
            //信息流
            mVpFragment.setCurrentItem(view.getId());
            setCurrentTab(view.getId());
        } else {//网页
            mVpFragment.setCurrentItem(view.getId());
            Bundle bundle = new Bundle();
            bundle.putString(Config.INFO_URL, mTitles.get(view.getId()).getUrl());
            mAdapter.getItem(view.getId()).setArguments(bundle);
            setCurrentTab(view.getId());
        }
    }


    private List<ContentFragment> mFragments = new ArrayList<>();


    /**
     * fragment pager adapter
     */
    public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return mFragments.get(arg0);
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }


    /**
     * @return
     */
    public boolean canGoBack() {
        FeedsLog.e("当前位置：" + mCurrentPosition);
        return mFragments.get(mCurrentPosition).canGoBack();
    }

    /**
     *
     */
    public void goBack() {
        FeedsLog.e("feed go back");
        mFragments.get(mCurrentPosition).goBack();
    }

}
