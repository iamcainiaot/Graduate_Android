package com.example.zt.graduate.main.activity.heart_wall.presenter;

import android.content.Context;

import com.example.zt.graduate.main.activity.heart_wall.iview.IAddHeartView;
import com.example.zt.graduate.main.activity.heart_wall.iview.ISetLikeView;
import com.example.zt.graduate.main.activity.heart_wall.model.request.AddHeartRequest;
import com.example.zt.graduate.main.activity.heart_wall.model.request.SetLikeRequest;
import com.example.zt.graduate.main.activity.heart_wall.model.response.AddHeartResponse;
import com.example.zt.graduate.main.activity.heart_wall.model.response.SetLikeResponse;
import com.example.zt.graduate.manager.HeartWallManager;

import java.util.List;

import lib_utils.MyLogUtil;
import lib_utils.net.RxSubscribe;
import mvp.BasePresenter;

/**
 * @author taozhu5
 * @date 2019年4月15日 15:46:56
 * @description 点赞
 */
public class SetLikePresenter extends BasePresenter<ISetLikeView> {

    private HeartWallManager mHeartWallManager = new HeartWallManager();
    private Context mContext;

    public SetLikePresenter(ISetLikeView view, Context context) {
        super(view);
        mContext = context;
    }

    /**
     * 发送心声
     *
     * @param userId      用户Id
     * @param userIdLiked 被点赞的用户Id
     */
    public void doSetLike(String userId, String userIdLiked, String heartId, boolean isLike) {
        SetLikeRequest setLikeRequest = new SetLikeRequest(userId, userIdLiked, heartId, isLike);
        mView.get().onSetLikeStart();
        mHeartWallManager.doSetLike(setLikeRequest,
                new RxSubscribe<List<SetLikeResponse>>(mContext, true) {
                    @Override
                    protected void _onNext(List<SetLikeResponse> setLikeResponses) {
                        MyLogUtil.d("成功了");
                        mView.get().onSetLikeReturned(setLikeResponses);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.get().onSetLikeError();
                        MyLogUtil.d(message);
                    }
                });
    }
}
