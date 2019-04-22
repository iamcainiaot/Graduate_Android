package com.example.zt.graduate.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zt.graduate.R;
import com.example.zt.graduate.app.UserApplication;

import lib_utils.db.entity.UserInfoTable;
import mvp.fragment.BaseMvpFragment;
import widget.RoundImageView;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 我的Fragment
 */
public class MySelfFragment extends BaseMvpFragment implements View.OnClickListener {
    /**
     * @return Fragment的对应实例
     */
    public static MySelfFragment getInstance(UserInfoTable userInfoTable) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_USER_INFO_TABLE, userInfoTable);
        MySelfFragment mySelfFragment = new MySelfFragment();
        mySelfFragment.setArguments(args);
        return mySelfFragment;
    }

    private static final String EXTRA_USER_INFO_TABLE = "extra_user_info_table";

    private UserInfoTable mUserInfoTable;
    private TextView tvUserName;
    private TextView tvLabel;
    private RoundImageView ivImage;
//    private TextView tvUserName;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int layoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUserInfoTable = bundle.getParcelable(EXTRA_USER_INFO_TABLE);
        }
    }

    @Override
    public void initView() {
        tvUserName = $(R.id.tv_username);
        tvLabel = $(R.id.tv_label);
        ivImage = $(R.id.image_head);
        tvUserName.setText(mUserInfoTable.getName());
        String label = mUserInfoTable.getLabel();
        if (label != null){
            tvLabel.setText(label);
        }else{
            tvLabel.setText("");
        }

        Glide.with(getContext()).load(mUserInfoTable.getImageUrl()).into(ivImage);

        $(R.id.rl_chatted).setOnClickListener(v -> {
            // TODO　去聊过天的列表
        });
        $(R.id.rl_notify).setOnClickListener(v -> {
            // TODO  去通知列表
        });
        $(R.id.rl_user_help).setOnClickListener(v -> {
            // TODO　去客服帮助页面
        });

    }

    @Override
    public void initEvent() {
        // do nothing
    }
}
