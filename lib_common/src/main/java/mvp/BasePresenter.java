package mvp;

import java.lang.ref.WeakReference;
import java.util.Objects;

/**
 * @author taozhu5
 * @date 2019/3/13 10:16
 * @description 描述
 */
public class BasePresenter<T extends IAddPresenterView> {

    protected WeakReference<T> mView;

    public BasePresenter(T view) {
        mView = new WeakReference(view);
        if (view != null) {
            view.addPresenter(this);
        }
    }

    public boolean isDetached() {
        return mView.get() == null;
    }

    public void detachView() {
        if (!isDetached()) {
            mView.clear();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasePresenter<?> that = (BasePresenter<?>) o;
        return Objects.equals(mView, that.mView);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mView);
    }
}
