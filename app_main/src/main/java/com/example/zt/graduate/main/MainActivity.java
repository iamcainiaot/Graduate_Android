package com.example.zt.graduate.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.main.adapter.MyPagerAdapter;
import com.example.zt.graduate.main.fragment.HeartWallFragment;
import com.example.zt.graduate.main.fragment.MySelfFragment;
import com.example.zt.graduate.main.fragment.SearchingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

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

    private BottomBarBadge unreadMessages;
    private BottomBar mBottomBar;
    private HeartWallFragment mHeartWallFragment;
    private SearchingFragment mSearchingFragment;
    private MySelfFragment mMySelfFragment;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoordinatorLayout mCoordinator = findViewById(R.id.mCoordinator);
        viewPager = findViewById(R.id.viewPager);
        initViewPager();

        mBottomBar = BottomBar.attachShy(mCoordinator, viewPager, savedInstanceState);
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //单击事件 menuItemId 是 R.menu.bottombar_menu 中 item 的 id
                switch (menuItemId) {
                    case R.id.menu_heartwall:
                        mBottomBar.setActiveTabColor(getResources().getColor(R.color.colorPrimary));
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_searching:
                        viewPager.setCurrentItem(1);
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
        setMsg();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存BottomBar的状态
        mBottomBar.onSaveInstanceState(outState);
    }


    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(null == mHeartWallFragment ? mHeartWallFragment = HeartWallFragment.getInstance() : mHeartWallFragment);
        fragmentList.add(null == mSearchingFragment ? mSearchingFragment = SearchingFragment.getInstance() : mSearchingFragment);
        fragmentList.add(null == mMySelfFragment ? mMySelfFragment = MySelfFragment.getInstance() : mMySelfFragment);
        // fragmentList.add(null == fourFragment ? fourFragment = FourFragment.newInstance() : fourFragment);
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

    private void setMsg() {
        // 为tab设置一个标签，“信息”提示的数字
        // 参数分别是：第几个tab；小圆圈的颜色；显示的数字
        unreadMessages = mBottomBar.makeBadgeForTabAt(1, "#FF0000", 13);

        // 设置显示或隐藏
        unreadMessages.show();
        //  unreadMessages.hide();
        // 设置显示的数字
        // unreadMessages.setCount(4);

        // 设置显示/消失动画的延迟时间
        unreadMessages.setAnimationDuration(200);

        // 如果不点它，它一直显示
        unreadMessages.setAutoShowAfterUnSelection(true);
    }

    @Override
    public int layoutId() {
        return R.layout.bottombar_viewpager_layout;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
}