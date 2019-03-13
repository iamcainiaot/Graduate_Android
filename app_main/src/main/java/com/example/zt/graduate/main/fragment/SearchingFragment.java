package  com.example.zt.graduate.main.fragment;

import android.os.Bundle;
import android.view.View;

import mvp.fragment.BaseMvpFragment;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 寻找Fragment
 */
public class SearchingFragment extends BaseMvpFragment implements View.OnClickListener {

    /**
     * @return Fragment的对应实例
     */
    public static SearchingFragment getInstance() {
        Bundle args = new Bundle();
        SearchingFragment searchingFragment = new SearchingFragment();
        searchingFragment.setArguments(args);
        return searchingFragment;
    }

    @Override
    public void onClick(View v) {
        // do nothing
    }

    @Override
    public int layoutId() {
        return 0;
    }

    @Override
    public void initData() {
        // do nothing
    }

    @Override
    public void initView() {
        // do nothing
    }

    @Override
    public void initEvent() {
        // do nothing
    }
}
