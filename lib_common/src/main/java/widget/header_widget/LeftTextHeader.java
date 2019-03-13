package widget.header_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.lib_common.R;

import mvp.BaseHeader;

/**
 * @author taozhu5
 * @date 2019/3/13 10:32
 * @description 左边文字，右边无的头部
 */

public class LeftTextHeader extends BaseHeader {

    private TextView mTvTitle;
    private TextView mTvLeft;

    public LeftTextHeader(Context context) {
        super(context);
    }

    public LeftTextHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftTextHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int concreteLayout() {
        return R.layout.layout_left_text_header;
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        mTvTitle = findViewById(R.id.tv_title);
        mTvLeft = (TextView) mLeftTitle;
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LeftTextHeader);
            //读取xml中标记的标题文本
            final CharSequence title = ta.getText(R.styleable.LeftTextHeader_centerTitle);
            if (!TextUtils.isEmpty(title)) {
                mTvTitle.setText(title);
            }
            //读取xml中标记的左边文本
            final CharSequence leftText = ta.getText(R.styleable.LeftTextHeader_leftText);
            if (!TextUtils.isEmpty(leftText)) {
                mTvLeft.setText(leftText);
            }
            ta.recycle();
        }
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public TextView getTvLeft() {
        return mTvLeft;
    }

}
