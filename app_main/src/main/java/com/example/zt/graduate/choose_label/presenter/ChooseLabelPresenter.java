package com.example.zt.graduate.choose_label.presenter;

import android.content.Context;

import com.example.zt.graduate.choose_label.iview.IChooseLabelView;
import com.example.zt.graduate.choose_label.model.request.ChooseLabelRequest;
import com.example.zt.graduate.choose_label.model.response.ChooseLabelResponse;
import com.example.zt.graduate.manager.UserInfoManager;

import java.util.List;

import lib_utils.MyLogUtil;
import lib_utils.net.RxSubscribe;
import mvp.BasePresenter;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆请求
 */
public class ChooseLabelPresenter extends BasePresenter<IChooseLabelView> {
    private UserInfoManager mUserInfoManager = new UserInfoManager();
    private Context mContext;

    public ChooseLabelPresenter(IChooseLabelView view, Context context) {
        super(view);
        mContext = context;
    }

    /**
     * 登陆请求
     */
    public void doChooseLabel(String userId, String label, boolean isMale, String imageUrl) {
        ChooseLabelRequest chooseLabelRequest = new ChooseLabelRequest(userId,label, isMale, imageUrl);
        mView.get().onChooseLabelStart();
        mUserInfoManager.doChooseLabel(chooseLabelRequest,
                new RxSubscribe<List<ChooseLabelResponse>>(mContext, true) {
                    @Override
                    protected void _onNext(List<ChooseLabelResponse> chooseLabelResponses) {
                        MyLogUtil.d("成功了");
                        mView.get().onChooseLabelReturned(chooseLabelResponses);
                    }

                    @Override
                    protected void _onError(String message) {
                        MyLogUtil.d(message);
                    }
                });
    }
}