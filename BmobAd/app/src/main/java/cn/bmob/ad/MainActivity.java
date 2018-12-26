package cn.bmob.ad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.ad.base.BaseActivity;
import cn.bmob.ad.fragment.FeedsFragment;
import cn.bmob.ad.fragment.HomeFragment;
import cn.bmob.ad.fragment.MineFragment;

/**
 * @author zhangchaozhou
 */
public class MainActivity extends BaseActivity {

    // Used to load the 'native-lib' library on application startup.

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigation mBottomNavigation;


    private HomeFragment mHomeFragment;
    private FeedsFragment mFeedsFragment;
    private MineFragment mMineFragment;

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mToolbar.setSubtitleTextColor(ContextCompat.getColor(mContext, android.R.color.white));


        mHomeFragment = new HomeFragment();
        mFeedsFragment = new FeedsFragment();
        mFeedsFragment.setTabBackgroundColor(R.color.colorPrimary);
        mMineFragment = new MineFragment();


        mBottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int id) {
                switch (id) {
                    case R.id.tab_home:
                        mToolbar.setSubtitle("Home");
                        mToolbar.setVisibility(View.VISIBLE);
                        switchContent(mHomeFragment);
                        break;

                    case R.id.tab_feeds:
                        mToolbar.setVisibility(View.GONE);
                        switchContent(mFeedsFragment);
                        break;

                    case R.id.tab_mine:
                        mToolbar.setSubtitle("Mine");
                        mToolbar.setVisibility(View.VISIBLE);
                        switchContent(mMineFragment);
                        break;
                    default:
                        break;
                }
            }
        });


    }



    /**
     * @param to
     */
    public void switchContent(Fragment to) {
        Fragment from = mCurrentFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment == null) {
            transaction.add(R.id.container, to).commit();
            mCurrentFragment = to;
            return;
        }
        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            if (!to.isAdded()) {
                if (from.isAdded()) {
                    transaction.hide(from).add(R.id.container, to).commit();
                } else {
                    transaction.add(R.id.container, to).commit();
                }
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment==mFeedsFragment){
            if (mFeedsFragment.canGoBack()){
                mFeedsFragment.goBack();
            }else {
                finish();
            }
        }else {
            finish();
        }
    }

}
