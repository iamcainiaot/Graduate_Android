package com.example.zt.graduate.main.activity.heart_wall.presenter;

import android.content.Context;

import com.example.zt.graduate.main.activity.heart_wall.iview.IAddHeartView;
import com.example.zt.graduate.main.activity.heart_wall.model.request.AddHeartRequest;
import com.example.zt.graduate.main.activity.heart_wall.model.response.AddHeartResponse;
import com.example.zt.graduate.manager.HeartWallManager;

import java.util.List;

import lib_utils.MyLogUtil;
import lib_utils.net.RxSubscribe;
import mvp.BasePresenter;

/**
 * @author taozhu5
 * @date 2019年4月15日 15:46:56
 * @description 新增心声
 */
public class AddHeartPresenter extends BasePresenter<IAddHeartView> {

    private HeartWallManager mHeartWallManager = new HeartWallManager();
    private Context mContext;

    public AddHeartPresenter(IAddHeartView view, Context context) {
        super(view);
        mContext = context;
    }

    /**
     * 发送心声
     *
     * @param imageUrl 图片的Url地址
     * @param content  内容
     */
    public void doAddHeart(String imageUrl, String content) {
        AddHeartRequest addHeartRequest = new AddHeartRequest(imageUrl, content);
        mView.get().onAddAllHeartStart();
        mHeartWallManager.doAddHeart(addHeartRequest,
                new RxSubscribe<List<AddHeartResponse>>(mContext, true) {
                    @Override
                    protected void _onNext(List<AddHeartResponse> addHeartResponses) {
                        MyLogUtil.d("成功了");
                        mView.get().onAddAllHeartReturned(addHeartResponses);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.get().onAddAllHeartError();
                        MyLogUtil.d(message);
                    }
                });
    }
}
