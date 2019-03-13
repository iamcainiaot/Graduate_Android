package widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * @author taozhu5
 * @date 2019/3/13 10:34
 * @description 走马灯TextView
 */
public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {

    public MarqueeTextView(Context context) {
        super(context);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // 产品不需要跑马灯效果了，统一改成...
        /*setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);*/
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine(true);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

} 