package widget.header_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_common.R;

import mvp.BaseHeader;

/**
 * @author taozhu5
 * @date 2019/3/13 10:32
 * @description 左边icon，右边无的头部
 */

public class LeftIconHeader extends BaseHeader {

    private ImageView mIvLeft;
    private TextView mTvTitle;

    public LeftIconHeader(Context context) {
        super(context);
    }

    public LeftIconHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftIconHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int concreteLayout() {
        return R.layout.layout_left_icon_header;
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        mTvTitle = findViewById(R.id.tv_title);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LeftIconHeader);
            //读取xml中标记的标题文本
            final CharSequence title = ta.getText(R.styleable.LeftIconHeader_centerTitle);
            if (!TextUtils.isEmpty(title)) {
                mTvTitle.setText(title);
            }
            int bgColor = ta.getColor(R.styleable.LeftIconHeader_bgColor,
                    getResources().getColor(R.color.transparent));
            setBackgroundColor(bgColor);
            ta.recycle();
        }
        mIvLeft = findViewById(R.id.v_left);
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public void setIvLeftIcon(int resId) {
        if (mIvLeft == null) return;

        mIvLeft.setImageResource(resId);
    }

}
