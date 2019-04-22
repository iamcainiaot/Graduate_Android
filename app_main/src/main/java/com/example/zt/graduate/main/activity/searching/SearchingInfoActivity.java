package com.example.zt.graduate.main.activity.searching;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.example.zt.graduate.R;
import com.example.zt.graduate.main.activity.heart_wall.iview.IAddHeartView;
import com.example.zt.graduate.main.activity.heart_wall.model.response.AddHeartResponse;
import com.example.zt.graduate.main.activity.heart_wall.presenter.AddHeartPresenter;
import com.example.zt.graduate.main.event.AssignHeartToHeartFragmentEvent;
import com.hotbitmapgg.nineimagelibrary.NineImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import lib_utils.CommonUtils;
import lib_utils.MyLogUtil;
import lib_utils.StringUtils;
import lib_utils.ToastUtil;
import mvp.BaseHeader;
import mvp.BaseMvpActivity;
import widget.header_widget.LeftIconHeader;
import widget.header_widget.LeftTextRightTextHeader;

/**
 * @author taozhu5
 * @date 2019/4/13 15:05
 * @description 找到好友之后
 */
public class SearchingInfoActivity extends BaseMvpActivity {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, SearchingInfoActivity.class);
        context.startActivity(intent);
    }

    /**
     * 从相册选择图片
     */
    private static final int IMAGE_REQUEST_CODE = 1;

    /**
     * 九宫格图片
     */
    private NineImageView nineImageView;
    private List<String> mUrlList = new ArrayList<>();
    private AddHeartPresenter mAddHeartPresenter;

    private EditText mEtContent;

    @Override
    public int layoutId() {
        return R.layout.activity_searching_info;
    }

    @Override
    public void initData() {
        // do nothing
    }

    @Override
    public void initView() {
        LeftIconHeader leftIconHeader = $(R.id.header);
        leftIconHeader.setOnTitleClickListener(new BaseHeader.OnTitleClickListener() {
            @Override
            public void onLeftClick(View view) {
                onBackPressed();
            }

            @Override
            public void onRightClick(View view) {
                // do nothing
            }
        });
    }

}