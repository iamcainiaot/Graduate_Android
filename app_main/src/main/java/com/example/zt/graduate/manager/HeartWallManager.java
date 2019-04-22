package com.example.zt.graduate.manager;

import com.example.zt.graduate.main.activity.heart_wall.model.request.AddHeartRequest;
import com.example.zt.graduate.main.activity.heart_wall.model.request.SetLikeRequest;
import com.example.zt.graduate.main.activity.heart_wall.model.response.AddHeartResponse;
import com.example.zt.graduate.main.activity.heart_wall.model.response.SetLikeResponse;
import com.example.zt.graduate.main.fragment.heart_wall.model.request.HeartRequest;
import com.example.zt.graduate.main.fragment.heart_wall.model.response.HeartResponse;
import com.example.zt.graduate.main.fragment.searching.model.response.SearchingResponse;
import com.example.zt.graduate.service.HeartWallService;

import java.util.List;

import lib_utils.net.NetHttpApi;
import lib_utils.net.RxHelper;
import lib_utils.net.RxSubscribe;


/**
 * @author taozhu5
 * @date 2019年4月9日 14:17:52
 * @description 心声墙Http管理类
 */
public class HeartWallManager {
    private HeartWallService mService;

    public HeartWallManager() {
        mService = NetHttpApi.getInstance().getService(HeartWallService.class);
    }

    /**
     * 获取所有心声请求
     */
    public void doGetHeartWall(HeartRequest heartRequest, RxSubscribe<List<HeartResponse>> subscribe) {
        mService.getHeartWall(heartRequest.getParams())
                .compose(RxHelper.<List<HeartResponse>>handleResult())
                .subscribe(subscribe);
    }

    /**
     * 获取所有心声请求
     */
    public void doAddHeart(AddHeartRequest addHeartRequest, RxSubscribe<List<AddHeartResponse>> subscribe) {
        mService.doAddHeart(addHeartRequest.getParams())
                .compose(RxHelper.<List<HeartResponse>>handleResult())
                .subscribe(subscribe);
    }

    /**
     * 获取所有心声请求
     */
    public void doSetLike(SetLikeRequest setLikeRequest, RxSubscribe<List<SetLikeResponse>> subscribe) {
        mService.doSetLike(setLikeRequest.getParams())
                .compose(RxHelper.<List<SetLikeResponse>>handleResult())
                .subscribe(subscribe);
    }
}