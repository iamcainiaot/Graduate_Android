package widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_common.R;

/**
 * @author taozhu5
 * @date 2019/3/21 08:38
 * @description 描述
 */
public class UrlMessageView extends RelativeLayout {
    /**
     * 解析的文本
     */
    private TextView tvLinkSoup;

    public UrlMessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_url_message, this);
        tvLinkSoup = findViewById(R.id.tv_link_jsoup);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UrlMessageView);
            //读取xml中标记的标题文本
            final CharSequence title = ta.getText(R.styleable.UrlMessageView_title);
            if (!TextUtils.isEmpty(title)) {
                tvLinkSoup.setText(title);
            }
            ta.recycle();
        }
    }

    public void setData(String linkSoup) {
        tvLinkSoup.setText(linkSoup);
    }
}
