package mvp.fragment;

import android.app.Dialog;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;

import com.example.lib_common.R;

import lib_utils.CommonUtils;
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
}