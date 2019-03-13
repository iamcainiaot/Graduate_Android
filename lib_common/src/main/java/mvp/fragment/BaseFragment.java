package mvp.fragment;

import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;

import mvp.BaseActivity;
import mvp.IBaseView;
import widget.LoadingDialog;

/**
 * @author taozhu5
 * @date 2019/3/13 10:30
 * @description BaseFragment
 */
public abstract class BaseFragment extends DialogFragment implements IBaseView, IBaseFragment {

    protected final String TAG = getClass().getSimpleName();

    protected BaseActivity mActivity;


    public BaseFragment() {
        // Required empty public constructor
    }


    /**
     * 获取当前Fragment状态
     *
     * @return true为正常 false为未加载或正在删除
     */
    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showProgress(flag, message);
            }
        }
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, "正在处理，请稍后...");
    }

    @Override
    public void hideProgress() {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.hideProgress();
            }
        }
    }

    @Override
    public void showToast(int resId) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(resId);
            }
        }
    }

    @Override
    public void showToast(String msg) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(msg);
            }
        }
    }

    @Override
    public void showLongToast(String msg) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showLongToast(msg);
            }
        }
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public void close() {

    }

    protected LoadingDialog mDefaultLoadingDialog;

    protected void dismissDefaultLoadingDialog() {
        if (mDefaultLoadingDialog != null && mDefaultLoadingDialog.isShowing()) {
            mDefaultLoadingDialog.dismiss();
        }
    }

    protected void showDefaultLoadingDialog(CharSequence title) {
        if (mDefaultLoadingDialog == null) {
            mDefaultLoadingDialog = new LoadingDialog.Builder(getActivity())
                    .setCanceledOnTouchOutside(false)
                    .setCancelable(false)
                    .build();
        }
        mDefaultLoadingDialog.setTitle(title);
        mDefaultLoadingDialog.show();
    }

    protected void showDefaultLoadingDialog(@StringRes int titleResId) {
        showDefaultLoadingDialog(getString(titleResId));
    }

}
