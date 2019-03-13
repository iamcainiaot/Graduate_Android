package com.example.zt.graduate.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.main.fragment.HeartWallFragment;
import com.example.zt.graduate.main.fragment.MySelfFragment;
import com.example.zt.graduate.main.fragment.SearchingFragment;

import lib_utils.ToastUtil;
import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/13 11:49
 * @description 主页
 */
public class MainActivity extends BaseMvpActivity {

    private enum FragmentType {
        /**
         * 心声墙
         */
        HEART_WALL,
        /**
         * 寻找
         */
        SEARCHING,
        /**
         * 我的
         */
        MYSELF
    }

    /**
     * 心声墙Fragment
     */
    private HeartWallFragment mHeartWallFragment;
    /**
     * 我的Fragment
     */
    private MySelfFragment mMySelfFragment;
    /**
     * 寻找Fragment
     */
    private SearchingFragment mSearchingFragment;
    /**
     * 心声墙
     */
    private View ctHeartWall;
    /**
     * 寻找
     */
    private View ctSearching;
    /**
     * 我的
     */
    private View ctMySelf;
    /**
     * 当前正在显示的Fragment
     */
    private Fragment mCurrentShowFragment;

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        // do nothing
    }

    @Override
    public void initView() {
        ctHeartWall = $(R.id.ct_heart_wall);
        ctMySelf = $(R.id.ct_myself);
        ctSearching = $(R.id.ct_searching);

        ctSearching.setOnClickListener((View v) -> {
            showFragment(FragmentType.SEARCHING);
        });
        ctMySelf.setOnClickListener((View v) -> {
            showFragment(FragmentType.MYSELF);
        });
        ctHeartWall.setOnClickListener((View v) -> {
            showFragment(FragmentType.HEART_WALL);
        });
        showFragment(FragmentType.SEARCHING);
    }

    /**
     * @author taozhu5
     * @date 2019/3/13 12:27
     * @description 显示fragment
     */
    public void showFragment(FragmentType fragmentType) {
        Fragment showFragment;
        TextView tvHeartWall = $(R.id.tv_heart_wall);
        ImageView ivHeartWall = $(R.id.iv_heart_wall);
        TextView tvSearching = $(R.id.tv_searching);
        ImageView ivSearching = $(R.id.iv_searching);
        TextView tvMyself = $(R.id.tv_myself);
        ImageView ivMyself = $(R.id.iv_myself);
        if (fragmentType == FragmentType.HEART_WALL) {
            if (mHeartWallFragment == null) {
                mHeartWallFragment = new HeartWallFragment();
            }
            showFragment = mHeartWallFragment;
            tvHeartWall.setSelected(true);
            ivHeartWall.setSelected(true);
            tvSearching.setSelected(false);
            ivSearching.setSelected(false);
            tvMyself.setSelected(false);
            ivMyself.setSelected(false);
        } else if (fragmentType == FragmentType.SEARCHING) {
            if (mSearchingFragment == null) {
                mSearchingFragment = new SearchingFragment();
            }
            showFragment = mSearchingFragment;
            tvHeartWall.setSelected(false);
            ivHeartWall.setSelected(false);
            tvSearching.setSelected(true);
            ivSearching.setSelected(true);
            tvMyself.setSelected(false);
            ivMyself.setSelected(false);
        } else {
            if (mMySelfFragment == null) {
                mMySelfFragment = new MySelfFragment();
            }
            showFragment = mMySelfFragment;
            tvHeartWall.setSelected(false);
            ivHeartWall.setSelected(false);
            tvSearching.setSelected(false);
            ivSearching.setSelected(false);
            tvMyself.setSelected(true);
            ivMyself.setSelected(true);
        }

        //说明是同样的
        if (mCurrentShowFragment == showFragment) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //  说明是第一次添加
        if (!showFragment.isAdded()) {
            transaction.add(R.id.fragment_container, showFragment);
            if (mCurrentShowFragment != null) {
                transaction.hide(mCurrentShowFragment);
            }
        } else {
            transaction.hide(mCurrentShowFragment).show(showFragment);
        }
        transaction.commitAllowingStateLoss();
        mCurrentShowFragment = showFragment;
    }
}
