package mvp;

/**
 * @author taozhu5
 * @date 2019/3/13 10:29
 * @description 描述
 */
public interface IAddPresenterView extends IBaseView {
    /**
     * 接口方法
     *
     * @param presenter 对应的Presenter对象
     */
    void addPresenter(BasePresenter presenter);
}
