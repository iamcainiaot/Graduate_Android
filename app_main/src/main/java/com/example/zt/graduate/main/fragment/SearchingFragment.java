package com.example.zt.graduate.main.fragment;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zt.graduate.R;
import com.example.zt.graduate.main.MainActivity;
import com.example.zt.graduate.main.activity.searching.SearchingInfoActivity;
import com.example.zt.graduate.main.fragment.searching.iview.ISearchingView;
import com.example.zt.graduate.main.fragment.searching.model.response.SearchingResponse;
import com.example.zt.graduate.main.fragment.searching.presenter.SearchingPresenter;

import java.util.List;

import lib_utils.ToastUtil;
import mvp.fragment.BaseMvpFragment;
import widget.FillScreenButton;
import widget.WaveView;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 寻找Fragment
 */
public class SearchingFragment extends BaseMvpFragment implements ISearchingView {

    /**
     * @return Fragment的对应实例
     */
    public static SearchingFragment getInstance() {
        Bundle args = new Bundle();
        SearchingFragment searchingFragment = new SearchingFragment();
        searchingFragment.setArguments(args);
        return searchingFragment;
    }

    private SearchingPresenter mSearchingPresenter;
    private WaveView mWaveView;
    private RelativeLayout mRlSearching;
    private Handler handler;
    private Animator animator;
    private FillScreenButton button;
    private View vRlClickMe;

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
        vRlClickMe = $(R.id.rl_click_me);
        button = $(R.id.bt_test);
        handler = new Handler();
        mWaveView = $(R.id.wv_wave_view);
        mRlSearching = $(R.id.rl_searching);
        mRlSearching.getBackground().setAlpha(0);
        // 开始寻找
        mRlSearching.setOnClickListener(v -> {
            getSearching();
        });
    }

    private void getSearching() {
        if (mSearchingPresenter == null || mSearchingPresenter.isDetached()) {
            mSearchingPresenter = new SearchingPresenter(this, getContext());
        }
        vRlClickMe.setVisibility(View.GONE);
        waveView();
    }

    /**
     * 波纹动起来
     */
    private void waveView() {
        mWaveView.setDuration(5000);
        mWaveView.setStyle(Paint.Style.FILL);
        mWaveView.setColor(getResources().getColor(R.color.dan_purple));
        mWaveView.setInterpolator(new LinearOutSlowInInterpolator());
        mWaveView.start();
        handler.postDelayed(() -> {
            mSearchingPresenter.doGetSearching();
        }, 2000);
    }

    @Override
    public void initEvent() {
        // do nothing
    }

    @Override
    public void onSearchingStart() {
    }

    @Override
    public void onSearchingReturned(List<SearchingResponse> searchingResponses) {
        mWaveView.stop();
        int xc = (button.getLeft() + button.getRight()) / 2;
        int yc = (button.getTop() + button.getBottom()) / 2;
        animator = ViewAnimationUtils.createCircularReveal(mRlSearching,
                xc, yc, 0, 1111);
        animator.setDuration(300);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                handler.postDelayed(() -> {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.mainActivity, SearchingInfoActivity.class);
                    startActivity(intent);
                    MainActivity.mainActivity.overridePendingTransition
                            (R.anim.anim_in, R.anim.anim_out);
                }, 200);
                vRlClickMe.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
        mRlSearching.getBackground().setAlpha(255);
        ToastUtil.showShort(getContext(), "正在进入他(她)的主页...", true);
    }

    @Override
    public void onSearchingError() {
        mWaveView.stop();
        vRlClickMe.setVisibility(View.VISIBLE);
        ToastUtil.showShort(getContext(), "阿偶，出错了，请稍后重试一下~", false);
    }
}
