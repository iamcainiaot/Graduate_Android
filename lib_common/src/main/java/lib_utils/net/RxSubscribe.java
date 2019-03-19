package lib_utils.net;

import android.app.ProgressDialog;
import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import lib_utils.CommonUtils;
import lib_utils.MyLogUtil;
import retrofit2.HttpException;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 回调封装
 */

public abstract class RxSubscribe<T> extends rx.Subscriber<T> {
    private Context mContext;
    private ProgressDialog mDialog;

    private boolean mShowDialog;

    protected RxSubscribe(Context context, boolean showDialog) {
        this.mContext = context;
        this.mShowDialog = showDialog;
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onCompleted() {
        MyLogUtil.d("请求完成");
        if (mDialog != null && mShowDialog) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    @Override
    public void onError(Throwable e) {
        if (!CommonUtils.isNetWorkConnected(mContext)) {
            _onError("网络不可用");
            //SocketTimeoutException:服务器响应超时
            //ConnectException:服务器请求超时
        } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
            _onError("请求超时,请稍后再试...");
        } else if (e instanceof HttpException) {
            _onError("服务器异常，请稍后再试...");
        } else {
            _onError(e.getMessage());
        }
        if (mDialog != null && mShowDialog) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
