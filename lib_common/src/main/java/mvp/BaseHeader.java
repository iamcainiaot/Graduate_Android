package mvp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lib_common.R;

/**
 * @author taozhu5
 * @date 2019/3/13 10:16
 * @description 基础头部
 */
public abstract class BaseHeader extends RelativeLayout {

    protected View mLeftTitle;
    protected View mRightTitle;

    public BaseHeader(Context context) {
        this(context, null);
    }

    public BaseHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(concreteLayout(), this);
        setBackgroundResource(R.color.transparent);

        mLeftTitle = findViewById(R.id.v_left);
        mRightTitle = findViewById(R.id.v_right);

        init(context, attrs);


        if (mLeftTitle != null) {
            mLeftTitle.setOnClickListener((View v) -> {
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onLeftClick(v);
                }
            });
        }

        if (mRightTitle != null)

        {
            mRightTitle.setOnClickListener((View v) -> {
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onRightClick(v);
                }
            });
        }

    }

    protected abstract int concreteLayout();

    protected abstract void init(Context context, AttributeSet attrs);

    private OnTitleClickListener mOnTitleClickListener;

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        mOnTitleClickListener = onTitleClickListener;
    }

    public interface OnTitleClickListener {
        void onLeftClick(View view);

        void onRightClick(View view);
    }

}
