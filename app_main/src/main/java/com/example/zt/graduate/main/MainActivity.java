package com.example.zt.graduate.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.graduate_android.R;
import com.example.zt.graduate.main.fragment.HeartWallFragment;
import com.example.zt.graduate.main.fragment.MySelfFragment;
import com.example.zt.graduate.main.fragment.SearchingFragment;

import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/13 11:49
 * @description 描述
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

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

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

    public void showFragment(FragmentType fragmentType) {
        TextView tvHeartWall = $(R.id.tv_heart_wall);
        ImageView ivHeartWall = $(R.id.iv_heart_wall);
        TextView tvSearching = $(R.id.tv_searching);
        ImageView ivSearching = $(R.id.iv_searching);
        TextView tvMyself = $(R.id.tv_myself);
        ImageView ivMyself = $(R.id.iv_myself);
        if (fragmentType == FragmentType.HEART_WALL) {
            if (mHeartWallFragment == null) {
                mHeartWallFragment = HeartWallFragment.getInstance();
            }
            tvHeartWall.setSelected(true);
            ivHeartWall.setSelected(true);
            tvSearching.setSelected(false);
            ivSearching.setSelected(false);
            tvMyself.setSelected(false);
            ivMyself.setSelected(false);
        } else if (fragmentType == FragmentType.SEARCHING) {
            if (mSearchingFragment == null) {
                mSearchingFragment = SearchingFragment.getInstance();
            }
            tvHeartWall.setSelected(false);
            ivHeartWall.setSelected(false);
            tvSearching.setSelected(true);
            ivSearching.setSelected(true);
            tvMyself.setSelected(false);
            ivMyself.setSelected(false);
        } else {
            if (mMySelfFragment == null) {
                mMySelfFragment = MySelfFragment.getInstance();
            }
            tvHeartWall.setSelected(false);
            ivHeartWall.setSelected(false);
            tvSearching.setSelected(false);
            ivSearching.setSelected(false);
            tvMyself.setSelected(true);
            ivMyself.setSelected(true);
        }

    }
}
