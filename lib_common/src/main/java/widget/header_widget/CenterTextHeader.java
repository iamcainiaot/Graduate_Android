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
 * @description 左边无，右边无的头部
 */

public class CenterTextHeader extends BaseHeader {

    private TextView mTvTitle;

    public CenterTextHeader(Context context) {
        super(context);
    }

    public CenterTextHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterTextHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int concreteLayout() {
        return R.layout.layout_center_text_header;
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        mTvTitle = findViewById(R.id.tv_title);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CenterTextHeader);
            //读取xml中标记的标题文本
            final CharSequence title = ta.getText(R.styleable.CenterTextHeader_centerTitle);
            if (!TextUtils.isEmpty(title)) {
                mTvTitle.setText(title);
            }
            ta.recycle();
        }
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }
}
