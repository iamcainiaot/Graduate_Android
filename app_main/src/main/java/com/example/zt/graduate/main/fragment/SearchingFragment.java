package com.example.zt.graduate.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.graduate_android.R;

import mvp.fragment.BaseMvpFragment;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 寻找Fragment
 */
public class SearchingFragment extends BaseMvpFragment implements View.OnClickListener {

    /**
     * @return Fragment的对应实例
     */
    public static SearchingFragment getInstance() {
        Bundle args = new Bundle();
        SearchingFragment searchingFragment = new SearchingFragment();
        searchingFragment.setArguments(args);
        return searchingFragment;
    }

    @Override
    public void onClick(View v) {
        // do nothing
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_searching;
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
