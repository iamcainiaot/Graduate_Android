package mvp.fragment;

/**
 * @author taozhu5
 * @date 2019/3/13 10:31
 * @description IBaseFragment接口
 */
public interface IBaseFragment {
    /**
     * 返回对应的layout布局文件
     *
     * @return layoutId
     */
    int layoutId();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化View
     */
    void initView();

    /**
     * 初始化Event
     */
    void initEvent();
}
