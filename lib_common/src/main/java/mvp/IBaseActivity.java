package mvp;

/**
 * @author taozhu5
 * @date 2019/3/13 10:10
 * @description 描述
 */
public interface IBaseActivity {
    /**
     * 返回layoutId
     *
     * @return layoutId
     */
    int layoutId();

    /**
     *初始化数据，一般是上个页面传递过来的Intent数据
     */
    void initData();

    /**
     * 初始化View
     */
    void initView();

    /**
     * 初始化Event，可以是
     */
    void initEvent();
}
