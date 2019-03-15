package mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import lib_utils.CommonUtils;
import widget.LoadingDialog;

/**
 * @author taozhu5
 * @date 2019/3/13 10:15
 * @description BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView, IBaseActivity {

    protected final String TAG = this.getClass().getName();

    protected FragmentManager fragmentManager;

    protected ProgressDialog mProgressDialog;

    protected boolean needClickHideSoftInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }


    /**
     * 点击空白  隐藏输入法
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                if (CommonUtils.isFastDoubleClick()) {//判断是否快速点击，避免重复点击
                    return true;
                }
                View view = getCurrentFocus();
                if (isHideInput(view, ev)) {
                    HideSoftInput(view.getWindowToken());
                }
            }
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {

        // 如果不需要 则立即返回
        if (!needClickHideSoftInput) {
            return needClickHideSoftInput;
        }
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom);
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        } else {
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();
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
        if (mProgressDialog == null) {
            return;
        }

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void close() {
        finish();
    }


    public <T extends View> T $(@IdRes int id) {
        return findViewById(id);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!disableOnSaveInstanceState()) {//注释掉，解决内存不足回来一些奇怪问题
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * 默认禁止调用onSaveInstanceState，解决内存不足回来一些奇怪问题，子类可以复写该方法自行处理
     *
     * @return
     */
    protected boolean disableOnSaveInstanceState() {
        return true;
    }

    protected LoadingDialog mDefaultLoadingDialog;

    protected void dismissDefaultLoadingDialog() {
        if (mDefaultLoadingDialog != null && mDefaultLoadingDialog.isShowing()) {
            mDefaultLoadingDialog.dismiss();
        }
    }

    protected void showDefaultLoadingDialog(CharSequence title) {
        if (mDefaultLoadingDialog == null) {
            mDefaultLoadingDialog = new LoadingDialog.Builder(this)
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

    protected BaseActivity _this() {
        return this;
    }

}
