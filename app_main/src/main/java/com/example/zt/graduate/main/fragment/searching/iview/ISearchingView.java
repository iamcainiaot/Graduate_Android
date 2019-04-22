package com.example.zt.graduate.main.fragment.searching.iview;

import com.example.zt.graduate.main.fragment.searching.model.response.SearchingResponse;

import java.util.List;

import mvp.IAddPresenterView;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:18:23
 * @description 匹配好友IView接口层
 */
public interface ISearchingView extends IAddPresenterView {
    /**
     * 登陆开始
     */
    void onSearchingStart();

    /**
     * 响应完成
     *
     * @param searchingResponses 返回数据
     */
    void onSearchingReturned(List<SearchingResponse> searchingResponses);

    /**
     * 错误返回
     */
    void onSearchingError();
}
