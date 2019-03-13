package com.example.zt.graduate.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.graduate_android.R;

import lib_utils.ToastUtil;
import mvp.fragment.BaseMvpFragment;

/**
 * @author taozhu5
 * @date 2019/3/13 09:54:50
 * @description 心声墙Fragment
 */
public class HeartWallFragment extends BaseMvpFragment implements View.OnClickListener {

    /**
     * @return Fragment的对应实例
     */
    public static HeartWallFragment getInstance() {
        Bundle args = new Bundle();
        HeartWallFragment heartWallFragment = new HeartWallFragment();
        heartWallFragment.setArguments(args);
        return heartWallFragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_heart_wall;
    }

    @Override
    public void initData() {
        // do nothing
    }

    @Override
    public void initView() {
        $(R.id.dsad).setOnClickListener((View v) -> {
            ToastUtil.showShort(getContext(), "ssssssssss");
        });
    }

    @Override
    public void initEvent() {
        // do nothing
    }

    @Override
    public void onClick(View v) {

    }
}
