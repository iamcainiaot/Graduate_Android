package com.example.zt.graduate.main.activity.heart_wall;

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
import widget.header_widget.LeftTextRightTextHeader;

/**
 * @author taozhu5
 * @date 2019/4/13 15:05
 * @description 编辑心声墙
 */
public class AssignHeartWallActivity extends BaseMvpActivity implements IAddHeartView {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, AssignHeartWallActivity.class);
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
        return R.layout.activity_assign_heart_wall;
    }

    @Override
    public void initData() {
        // do nothing
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
                // 确定 上传
                submitHeart();
            }
        });
        mEtContent = $(R.id.et_content);
        nineImageView = $(R.id.ni_nine_image_view);
        nineImageView.setOnClickItemListener((i, arrayList) -> {
            // 加载图片
            CommonUtils.imagePreview(AssignHeartWallActivity.this, arrayList.get(i));
        });

        $(R.id.iv_add).setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        });
    }

    /**
     * 提交心声
     */
    private void submitHeart() {
        if (mAddHeartPresenter == null || mAddHeartPresenter.isDetached()) {
            mAddHeartPresenter = new AddHeartPresenter(this, this);
        }
        String content = mEtContent.getText().toString().trim();
        if (StringUtils.isEmpty(content)) {
            ToastUtil.showShort(AssignHeartWallActivity.this, "请输入内容", false);
            return;
        }
        StringBuilder sb = new StringBuilder();
        int size = mUrlList.size();
        for (int i = 0; i < size; i++) {
            sb.append(mUrlList.get(i));
            if (i != size - 1) {
                sb.append(",");
            }
        }
        MyLogUtil.d(sb.toString());
        mAddHeartPresenter.doAddHeart(sb.toString(), mEtContent.getText().toString().trim());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            //这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
            case IMAGE_REQUEST_CODE:
                //resultcode是setResult里面设置的code值
                if (resultCode == RESULT_OK) {
                    try {
                        // 获取系统返回的照片的Uri

                        if (data != null) {
                            Uri selectedImage = data.getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            //从系统表中查询指定Uri对应的照片
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                            }
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            // 获取照片路径
                            String path = cursor.getString(columnIndex);
                            cursor.close();
                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                            mUrlList.add(path);
                            nineImageView.setImageUrls(mUrlList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onAddAllHeartStart() {
        showProgress();
    }

    @Override
    public void onAddAllHeartReturned(List<AddHeartResponse> addAllHeartResponses) {
        hideProgress();
        ToastUtil.showShort(this, "发心声成功~", true);
        EventBus.getDefault().post(new AssignHeartToHeartFragmentEvent("发送成功！"));
        finish();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onAddAllHeartError() {
        hideProgress();
    }
}