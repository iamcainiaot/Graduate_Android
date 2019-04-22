package com.example.zt.graduate.service;


import com.example.zt.graduate.main.activity.heart_wall.model.response.SetLikeResponse;
import com.example.zt.graduate.main.fragment.heart_wall.model.response.HeartResponse;

import java.util.List;
import java.util.Map;

import mvp.BaseResponse;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:17:56
 * @description 心声墙相关请求Service
 */
public interface HeartWallService {

    /**
     * 获取全部心声
     *
     * @param params 参数
     * @return List<ChooseLabelResponse>
     */
    @FormUrlEncoded
    @POST("heartWall/getAllHeart")
    Observable<BaseResponse<List<HeartResponse>>> getHeartWall(@FieldMap Map<String, String> params);

    /**
     * 增加心声
     *
     * @param params 参数
     * @return List<HeartResponse>
     */
    @FormUrlEncoded
    @POST("heartWall/addHeart")
    Observable<BaseResponse<List<HeartResponse>>> doAddHeart(@FieldMap Map<String, String> params);

    /**
     * 点赞
     *
     * @param params 参数
     * @return List<ChooseLabelResponse>
     */
    @FormUrlEncoded
    @POST("heartWall/setLike")
    Observable<BaseResponse<List<SetLikeResponse>>> doSetLike(@FieldMap Map<String, String> params);
}