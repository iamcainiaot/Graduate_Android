package widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_common.R;

/**
 * @author taozhu5
 * @date 2019/3/13 10:34
 * @description 加载框Dialog
 */
public class LoadingDialog extends Dialog {

    private ImageView mIvLoading;
    private TextView mTvTitle;

    private boolean isCanceledOnTouchOutside = false;
    private boolean isCancelable = false;
    private CharSequence title;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading_dialog);
        mIvLoading = findViewById(R.id.iv_loading);
        mTvTitle = findViewById(R.id.tv_title);
        setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        setCancelable(isCancelable);
//        showLoadingGif();
        mTvTitle.setText(title);
        getWindow().setDimAmount(0);//背景不要变暗
    }

    /**
     * 显示加载动画gif
     */
  /*  private void showLoadingGif() {
        ImgLoader.INSTANCE.loadGifImage("file:///android_asset/global_loading_gif.gif", mIvLoading);
    }*/
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
        this.isCanceledOnTouchOutside = cancel;
    }

    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        this.isCancelable = flag;
    }

    @Override
    public void setTitle(@StringRes int title) {
        setTitle(getContext().getString(title));
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        if (isShowing()) {
            mTvTitle.setText(title);
        }
    }

    @Override
    public void show() {
        super.show();
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    public static class Builder {

        private LoadingDialog mLoadingDialog;

        public Builder(Context context) {
            mLoadingDialog = new LoadingDialog(context);
        }

        public Builder setCanceledOnTouchOutside(boolean cancel) {
            mLoadingDialog.setCanceledOnTouchOutside(cancel);
            return this;
        }

        public Builder setCancelable(boolean flag) {
            mLoadingDialog.setCancelable(flag);
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            mLoadingDialog.setTitle(title);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            mLoadingDialog.setTitle(title);
            return this;
        }

        public LoadingDialog build() {
            return mLoadingDialog;
        }
    }
}