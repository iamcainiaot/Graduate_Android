package com.example.zt.graduate.main.activity.heart_wall;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zt.graduate.R;
import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.main.activity.heart_wall.iview.ISetLikeView;
import com.example.zt.graduate.main.activity.heart_wall.model.response.SetLikeResponse;
import com.example.zt.graduate.main.activity.heart_wall.presenter.SetLikePresenter;
import com.example.zt.graduate.main.chat.ChatMainActivity;
import com.example.zt.graduate.main.fragment.heart_wall.model.response.HeartResponse;
import com.hotbitmapgg.nineimagelibrary.NineImageView;

import java.util.List;

import lib_utils.CommonUtils;
import lib_utils.StringUtils;
import lib_utils.ToastUtil;
import me.yifeiyuan.library.PeriscopeLayout;
import mvp.BaseHeader;
import mvp.BaseMvpActivity;
import widget.RoundImageView;
import widget.header_widget.LeftTextRightTextHeader;

/**
 * @author taozhu5
 * @date 2019/4/13 15:05
 * @description 心声墙详情页面
 */
public class SingleHeartWallActivity extends BaseMvpActivity implements ISetLikeView {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context, HeartResponse heartResponse) {
        Intent intent = new Intent(context, SingleHeartWallActivity.class);
        intent.putExtra(EXTRA_HEART_WALL_RESPONSE, heartResponse);
        context.startActivity(intent);
    }

    private static final String EXTRA_HEART_WALL_RESPONSE = "extra_heart_wall_response";
    private static final String MALE = "男";
    /**
     * 上个页面传递的数据
     */
    private HeartResponse mHeartResponse;

    private boolean isLike;
    /**
     * 点赞
     */
    private PeriscopeLayout ivLike;
    private SetLikePresenter mSetLikePresenter;

    @Override
    public int layoutId() {
        return R.layout.activity_single_heart_wall;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mHeartResponse = intent.getParcelableExtra(EXTRA_HEART_WALL_RESPONSE);
            String userId = UserApplication.getCurrentId();
            userId.trim();
        }
    }

    @Override
    public void initView() {

        LeftTextRightTextHeader leftTextRightTextHeader = $(R.id.header);
        leftTextRightTextHeader.setOnTitleClickListener(new BaseHeader.OnTitleClickListener() {
            @Override
            public void onLeftClick(View view) {
                onBackPressed();
            }

            @Override
            public void onRightClick(View view) {
                // do nothing
            }
        });

        TextView tvContent;
        TextView tvLabel;
        RoundImageView ivHeartWallUserImage;
        ImageView ivSex;
        NineImageView nineImageView;
        TextView tvUserName;
        View tv2Know;

        tvContent = $(R.id.tv_content);
        ivHeartWallUserImage = $(R.id.iv_heart_wall_user_image);
        ivSex = $(R.id.iv_sex);
        tvLabel = $(R.id.tv_label);
        ivLike = $(R.id.periscope);
        tvUserName = $(R.id.tv_username);
        tv2Know = $(R.id.tv_2_know);

        // 用户名
        tvUserName.setText(mHeartResponse.getUserName());
        // 性别
        String sex = mHeartResponse.getSex();
        if (StringUtils.isEqual(sex, MALE)) {
            CommonUtils.setViewBackground(ivSex, R.drawable.male);
        } else {
            CommonUtils.setViewBackground(ivSex, R.drawable.female);
        }
        // 个性标签
        tvLabel.setText(mHeartResponse.getLabel());

        // 用户头像
        Glide.with(this).load(mHeartResponse.getImageUrl()).
                placeholder(R.drawable.icon_default_people).into(ivHeartWallUserImage);

        // 内容
        tvContent.setText(mHeartResponse.getContent());

        nineImageView = $(R.id.ni_nine_image_view);
        // 显示图片
        List<String> list = mHeartResponse.getHeartImageUrl();
        if (!CommonUtils.isEmpty(list) && !StringUtils.isEmpty(list.get(0))) {
            nineImageView.setVisibility(View.VISIBLE);
            nineImageView.setVisibility(View.VISIBLE);
            nineImageView.setImageUrls(list);
            nineImageView.setOnClickItemListener((i, arrayList) -> {
                // 加载图片
                CommonUtils.imagePreview(this, arrayList.get(i));
            });

            nineImageView.setOnClickItemListener((i, arrayList) -> {
                // 加载图片
                CommonUtils.imagePreview(SingleHeartWallActivity.this, arrayList.get(i));
            });
        } else {
            nineImageView.setVisibility(View.GONE);
        }

        // 点赞
        ivLike.setOnClickListener(v -> {
            if (!isLike) {
                if (mSetLikePresenter == null || mSetLikePresenter.isDetached()) {
                    mSetLikePresenter = new SetLikePresenter(this, this);
                }
                mSetLikePresenter.doSetLike(UserApplication.getCurrentId(),
                        mHeartResponse.getUserId(), mHeartResponse.getHeartId(), true);
            } else {
                mSetLikePresenter.doSetLike(UserApplication.getCurrentId(),
                        mHeartResponse.getUserId(), mHeartResponse.getHeartId(), true);
            }
        });

        // 点击认识一下，要把你想认识的那个人的用户名传过去
        tv2Know.setOnClickListener(v -> {
            ChatMainActivity.start(this, mHeartResponse.getUserName(), mHeartResponse.getImageUrl());
        });
    }

    @Override
    public void onSetLikeStart() {
        // do nothing
    }

    @Override
    public void onSetLikeReturned(List<SetLikeResponse> setLikeResponses) {
        if (!isLike) {
            ivLike.addHeart();
            CommonUtils.setViewBackground(ivLike, R.drawable.icon_like_select);
            isLike = true;
        } else {
            CommonUtils.setViewBackground(ivLike, R.drawable.icon_like_unselect);
            isLike = false;
        }
    }

    @Override
    public void onSetLikeError() {
        ToastUtil.showShort(_this(), "点赞失败，网络好像出了问题~", false);
    }

    //FIXME   获取SdCard中的数据
    // if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
    //   // SD卡已装入
    //   //  File file = getEx;
    //   File sdCard1 = FileUtil.getExternalFilesDir(this, "Pictures");
    //   // File directoryPictures = new File(sdCard1, "/知乎/v2-212bd398d9d4a645f4d2fc60d7068438.jpg");
    //   String url = sdCard1 + "/知乎/v2-28f32645e5aa9e862ec0f3afd626a9ce.jpg";
    //   String url1 = "/sdcard/Picturses/知乎/v2-28f32645e5aa9e862ec0f3afd626a9ce.jpg";
    //   // Glide.with(getContext()).load(url1).placeholder(R.drawable.icon_default).into(ivUserImage);
    //   } else {
    //   ToastUtil.showShort(this, "没有SDCard", false);
    //   }
}