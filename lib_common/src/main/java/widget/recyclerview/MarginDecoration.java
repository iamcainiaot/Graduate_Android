package widget.recyclerview;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author taozhu5
 * @date 2019/3/20 10:08
 * @description RecyclerView的GridLayoutManager的间距
 */
public class MarginDecoration extends RecyclerView.ItemDecoration {
    /**
     * 间距
     */
    private int space;

    public MarginDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.top = space * 3;
    }
}
