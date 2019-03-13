package mvp.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import mvp.BasePresenter;

/**
 * @author taozhu5
 * @date 2019/3/13 10:30
 * @description BaseMvpFragment
 */
public abstract class BaseMvpFragment extends BaseFragment {
    private Set<BasePresenter> mPresenters;

    /**
     * 子类每次new一个presenter的时候，请调用此方法
     *
     * @param presenter 对应presenter对象
     */
    public void addPresenter(BasePresenter presenter) {
        if (mPresenters == null) {
            mPresenters = new HashSet<>();
        }
        if (!mPresenters.contains(presenter)) {
            mPresenters.add(presenter);
        }
    }

    protected View mRootView;

    public BaseMvpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getBaseActivity();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(mActivity).inflate(layoutId(), container, false);
        initView();
        initEvent();
        // if (!setMotionEventSplittingEnabled() && mRootView instanceof ViewGroup) {
        //     // CommonUtils.setMotionEventSplittingEnabled((ViewGroup) mRootView, false);//设置不可以多点点击
        // }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        if (mPresenters != null) {
            for (BasePresenter presenter : mPresenters) {
                presenter.detachView();
            }
            mPresenters = null;
        }
        super.onDestroy();
    }

    public <T extends View> T $(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    /**
     * 是否可以多点点击 子类可以复写该方法 默认不可多点点击
     *
     * @return
     */
    protected boolean setMotionEventSplittingEnabled() {
        return false;
    }
}
