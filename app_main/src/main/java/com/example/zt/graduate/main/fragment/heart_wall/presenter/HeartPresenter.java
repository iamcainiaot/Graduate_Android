package com.example.zt.graduate.main.fragment.heart_wall.presenter;

import android.content.Context;

import com.example.zt.graduate.main.fragment.heart_wall.iview.IHeartView;
import com.example.zt.graduate.main.fragment.heart_wall.model.request.HeartRequest;
import com.example.zt.graduate.main.fragment.heart_wall.model.response.HeartResponse;
import com.example.zt.graduate.manager.HeartWallManager;
import com.example.zt.graduate.manager.UserInfoManager;

import java.util.List;

import lib_utils.MyLogUtil;
import lib_utils.net.RxSubscribe;
import mvp.BasePresenter;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:18:03
 * @description 心声墙请求
 */
public class HeartPresenter extends BasePresenter<IHeartView> {
    private HeartWallManager mHeartWallManager = new HeartWallManager();
    private Context mContext;

    public HeartPresenter(IHeartView view, Context context) {
        super(view);
        mContext = context;
    }

    /**
     * 获取全部心声
     */
    public void doGetHeartWall() {
        HeartRequest heartRequest = new HeartRequest();
        mView.get().onHeartStart();
        mHeartWallManager.doGetHeartWall(heartRequest,
                new RxSubscribe<List<HeartResponse>>(mContext, true) {
                    @Override
                    protected void _onNext(List<HeartResponse> heartResponses) {
                        MyLogUtil.d("成功了");
                        mView.get().onHeartReturned(heartResponses);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.get().onHeartError();
                        MyLogUtil.d(message);
                    }
                });
    }
}
