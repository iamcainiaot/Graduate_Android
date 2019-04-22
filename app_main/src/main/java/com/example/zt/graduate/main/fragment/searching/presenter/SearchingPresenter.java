package com.example.zt.graduate.main.fragment.searching.presenter;

import android.content.Context;

import com.example.zt.graduate.main.fragment.heart_wall.iview.IHeartView;
import com.example.zt.graduate.main.fragment.heart_wall.model.response.HeartResponse;
import com.example.zt.graduate.main.fragment.searching.iview.ISearchingView;
import com.example.zt.graduate.main.fragment.searching.model.request.SearchingRequest;
import com.example.zt.graduate.main.fragment.searching.model.response.SearchingResponse;
import com.example.zt.graduate.manager.UserInfoManager;

import java.util.List;

import lib_utils.MyLogUtil;
import lib_utils.net.RxSubscribe;
import mvp.BasePresenter;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:18:03
 * @description 匹配好友请求
 */
public class SearchingPresenter extends BasePresenter<ISearchingView> {
    private UserInfoManager userInfoManager = new UserInfoManager();
    private Context mContext;

    public SearchingPresenter(ISearchingView view, Context context) {
        super(view);
        mContext = context;
    }

    /**
     * 获取匹配的好友
     */
    public void doGetSearching() {
        SearchingRequest searchingRequest = new SearchingRequest();
        mView.get().onSearchingStart();
        userInfoManager.doSearching(searchingRequest,
                new RxSubscribe<List<SearchingResponse>>(mContext, true) {
                    @Override
                    protected void _onNext(List<SearchingResponse> searchingResponses) {
                        MyLogUtil.d("成功了");
                        mView.get().onSearchingReturned(searchingResponses);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.get().onSearchingError();
                        MyLogUtil.d(message);
                    }
                });
    }
}
