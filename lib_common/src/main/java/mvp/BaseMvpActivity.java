package mvp;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

/**
 * @author taozhu5
 * @date 2019/3/13 10:16
 * @description 描述
 */
public abstract class BaseMvpActivity extends BaseActivity implements IBaseMvpActivity, IAddPresenterView {

    private Set<BasePresenter> mPresenters;

    protected BaseHeader mHeader;

    /**
     * 状态栏背景色
     */
    protected @ColorInt
    int mStatusBarColor;

    /**
     * 子类每次new一个presenter的时候，需调用此方法
     *
     * @param presenter 请求
     */
    @Override
    public void addPresenter(BasePresenter presenter) {
        if (mPresenters == null) {
            mPresenters = new HashSet<>();
        }
        if (!mPresenters.contains(presenter)) {
            mPresenters.add(presenter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initData();
        initView();
        initEvent();
    }

    @Override
    public void initEvent() {
        if (mHeader != null) {
            mHeader.setOnTitleClickListener(new BaseHeader.OnTitleClickListener() {
                @Override
                public void onLeftClick(View view) {
                    finish();
                }

                @Override
                public void onRightClick(View view) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenters != null) {
            for (BasePresenter presenter : mPresenters) {
                presenter.detachView();
            }
            mPresenters = null;
        }
        super.onDestroy();
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showLongToast(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showProgress(boolean flag, String message) {

    }

    @Override
    public void showProgress(boolean flag) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(int resId) {

    }

    @Override
    public void close() {

    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public <T extends View> T $(@IdRes int id) {
        return findViewById(id);
    }

    public int getStatusBarColor() {
        return mStatusBarColor;
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
