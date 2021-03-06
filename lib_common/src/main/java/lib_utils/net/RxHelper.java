package lib_utils.net;

import lib_utils.MyLogUtil;
import mvp.BaseResponse;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author taozhu5
 * @date 2019/2/24 10:39
 * @description 处理返回数据
 */

public class RxHelper {
    /**
     * 用来统一处理Http的resultCode,并将返回的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public static <T> Observable.Transformer<BaseResponse<T>, T> handleResult() {
        return new Observable.Transformer<BaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponse<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> result) {
                        return Observable.create(new Observable.OnSubscribe<T>() {
                            @Override
                            public void call(Subscriber<? super T> subscriber) {
                                if (result.getStatus() == BaseResponse.CODE_OK) {
                                    if (result.getData() != null) {
                                        try {
                                            MyLogUtil.d("RxHelper", "返回数据：" + result.getData());
                                            subscriber.onNext(result.getData());
                                        } catch (Exception e) {
                                        }
                                    } else {
                                        // 也可不返回数据体，只需要证明请求成功了即可
                                    }
                                    subscriber.onCompleted();
                                } else {
                                    MyLogUtil.d("RxHelper", "出错了！");
                                }
                            }
                        });
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}