package com.example.zt.graduate.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.zt.graduate.R;
import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.choose_label.ChooseLabelActivity;
import com.example.zt.graduate.main.adapter.MyPagerAdapter;
import com.example.zt.graduate.main.fragment.HeartWallFragment;
import com.example.zt.graduate.main.fragment.MySelfFragment;
import com.example.zt.graduate.main.fragment.SearchingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import lib_utils.MyLogUtil;
import lib_utils.ToastUtil;
import lib_utils.db.entity.UserInfoTable;
import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/13 11:49
 * @description 主页
 */
public class MainActivity extends BaseMvpActivity {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static MainActivity mainActivity;
    private BottomBar mBottomBar;
    private HeartWallFragment mHeartWallFragment;
    private SearchingFragment mSearchingFragment;
    private MySelfFragment mMySelfFragment;
    private ViewPager viewPager;

    /**
     * GreenDao相关操作
     */
    private UserApplication mUserApplication;
    private UserInfoTable mUserInfoTable;

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(null == mHeartWallFragment ? mHeartWallFragment = HeartWallFragment.getInstance() : mHeartWallFragment);
        fragmentList.add(null == mSearchingFragment ? mSearchingFragment = SearchingFragment.getInstance() : mSearchingFragment);
        if (mMySelfFragment == null) {
            mMySelfFragment = MySelfFragment.getInstance(mUserInfoTable);
        }
        fragmentList.add(mMySelfFragment);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public int layoutId() {
        return R.layout.bottombar_viewpager_layout;
    }

    @Override
    public void initData() {
        mUserApplication = (UserApplication) getApplication();
        mUserInfoTable = mUserApplication.getUserInfoTable();
    }

    @Override
    public void setStatus() {
        setStatusMVP();
    }

    @Override
    public void initView() {
        mainActivity = this;
        CoordinatorLayout mCoordinator = findViewById(R.id.mCoordinator);
        viewPager = findViewById(R.id.viewPager);
        initViewPager();

        mBottomBar = BottomBar.attachShy(mCoordinator, viewPager, savedInstanceState);
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.useOnlyStatusBarTopOffset();
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //单击事件 menuItemId 是 R.menu.bottombar_menu 中 item 的 id
                switch (menuItemId) {
                    case R.id.menu_heartwall:
                        viewPager.setCurrentItem(0);
                        mBottomBar.setActiveTabColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case R.id.menu_searching:
                        viewPager.setCurrentItem(1);
                        // 让底部栏显示
                        mBottomBar.hide();
                        mBottomBar.setActiveTabColor(getResources().getColor(R.color.colorAccent));
                        break;
                    case R.id.menu_myself:
                        mBottomBar.setActiveTabColor(getResources().getColor(R.color.green));
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                //重选事件，当前已经选择了这个，又点了这个tab。微博点击首页刷新页面
            }
        });
        viewPager.setOffscreenPageLimit(3);

        JiGuangLogin();
    }

    // 极光登陆
    private void JiGuangLogin() {
        if (mUserInfoTable != null) {
            // 注册成功，开始登陆
            String password = mUserInfoTable.getUserId().substring(24);
            JMessageClient.login(mUserInfoTable.getName(), password,
                    new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            JMessageClient.getUserInfo(mUserInfoTable.getName(), new GetUserInfoCallback() {
                                @Override
                                public void gotResult(int i, String s, UserInfo userInfo) {
                                    MyLogUtil.d("极光IM 登陆成功", "userInfo:" + userInfo.toString());
                                }
                            });
                        }
                    });
        }
    }
}