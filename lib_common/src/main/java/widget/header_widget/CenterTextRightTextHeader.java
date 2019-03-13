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
 * @description 中间文字，右边文字的头部
 */

public class CenterTextRightTextHeader extends BaseHeader {

    private TextView mTvTitle;
    private TextView mTvRight;

    public CenterTextRightTextHeader(Context context) {
        super(context);
    }

    public CenterTextRightTextHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterTextRightTextHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int concreteLayout() {
        return R.layout.layout_center_text_right_text_header;
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        mTvTitle = findViewById(R.id.tv_title);
        mTvRight = (TextView) mRightTitle;
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CenterTextRightTextHeader);
            //读取xml中标记的标题文本
            final CharSequence title = ta.getText(R.styleable.CenterTextRightTextHeader_centerTitle);
            if (!TextUtils.isEmpty(title)) {
                mTvTitle.setText(title);
            }
            //读取xml中标记的右边文本
            final CharSequence rightText = ta.getText(R.styleable.CenterTextRightTextHeader_rightText);
            if (!TextUtils.isEmpty(rightText)) {
                mTvRight.setText(rightText);
            }
            ta.recycle();
        }
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public TextView getTvRight() {
        return mTvRight;
    }
}
