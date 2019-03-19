package com.example.zt.graduate.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.graduate_android.R;

import lib_utils.MyLogUtil;
import mvp.fragment.BaseMvpFragment;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 我的Fragment
 */
public class MySelfFragment extends BaseMvpFragment implements View.OnClickListener {
    /**
     * @return Fragment的对应实例
     */
    public static MySelfFragment getInstance() {
        Bundle args = new Bundle();
        MySelfFragment mySelfFragment = new MySelfFragment();
        mySelfFragment.setArguments(args);
        return new MySelfFragment();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int layoutId() {
        MyLogUtil.d("--------到了我自己的页面来了");
        return R.layout.fragment_myself;
    }

    @Override
    public void initData() {
        // do nothing
    }

    @Override
    public void initView() {
        // do nothing
    }

    @Override
    public void initEvent() {
        // do nothing
    }
}
