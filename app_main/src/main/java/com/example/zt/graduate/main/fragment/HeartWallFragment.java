package com.example.zt.graduate.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zt.graduate.R;
import com.example.zt.graduate.main.activity.heart_wall.AssignHeartWallActivity;
import com.example.zt.graduate.main.activity.heart_wall.SingleHeartWallActivity;
import com.example.zt.graduate.main.event.AssignHeartToHeartFragmentEvent;
import com.example.zt.graduate.main.fragment.heart_wall.iview.IHeartView;
import com.example.zt.graduate.main.fragment.heart_wall.model.response.HeartResponse;
import com.example.zt.graduate.main.fragment.heart_wall.presenter.HeartPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import lib_utils.CommonUtils;
import lib_utils.ToastUtil;
import mvp.BaseHeader;
import mvp.fragment.BaseMvpFragment;
import widget.RoundImageView;
import widget.header_widget.LeftTextRightTextHeader;

/**
 * @author taozhu5
 * @date 2019/3/13 09:54:50
 * @description 心声墙Fragment
 */
public class HeartWallFragment extends BaseMvpFragment implements View.OnClickListener, IHeartView {

    /**
     * @return Fragment的对应实例
     */
    public static HeartWallFragment getInstance() {
        Bundle args = new Bundle();
        HeartWallFragment heartWallFragment = new HeartWallFragment();
        heartWallFragment.setArguments(args);
        return heartWallFragment;
    }

    private static final String MALE = "男";

    /**
     * 心声适配器
     */
    private HeartAdapter mHeartAdapter;

    /**
     * 心声列表
     */
    private RecyclerView mRvHeartWall;

    /**
     * 心声墙请求Presenter
     */
    private HeartPresenter mHeartPresenter;

    /**
     * 下拉刷新
     */
    private SmartRefreshLayout mSmartRefreshLayout;
    private View mVError;
    private View mVContent;

    @Override
    public int layoutId() {
        return R.layout.fragment_heart_wall;
    }

    @Override
    public void initData() {
        // 没有注册过再注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void initView() {
        mVError = $(R.id.view_error);
        mVContent = $(R.id.ll_content);
        mSmartRefreshLayout = $(R.id.refresh);
        // 关闭上拉加载更多
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> refresh());
        // 自动刷新
        mSmartRefreshLayout.autoRefresh();

        mRvHeartWall = $(R.id.rv_heart_wall);

        LeftTextRightTextHeader header = $(R.id.header);
        header.setOnTitleClickListener(new BaseHeader.OnTitleClickListener() {
            @Override
            public void onLeftClick(View view) {
                // do nothing
            }

            @Override
            public void onRightClick(View view) {
                // 跳转到发布心声页面
                AssignHeartWallActivity.start(getContext());
            }
        });
        // 获取全部心声
        getHeartList();
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
        getHeartList();
    }

    /**
     * 获取全部心声
     */
    private void getHeartList() {
        if (mHeartPresenter == null || mHeartPresenter.isDetached()) {
            mHeartPresenter = new HeartPresenter(this, getContext());
        }
        mHeartPresenter.doGetHeartWall();
    }

    @Override
    public void initEvent() {
        // do nothing
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onHeartStart() {
        // do nothing
    }

    @Override
    public void onHeartReturned(List<HeartResponse> loginResponse) {
        mVContent.setVisibility(View.VISIBLE);
        mVError.setVisibility(View.GONE);
        mSmartRefreshLayout.finishRefresh();
        if (loginResponse != null) {
            if (mHeartAdapter == null) {
                mHeartAdapter = new HeartAdapter(loginResponse);

                mRvHeartWall.setAdapter(mHeartAdapter);
            } else {
                mHeartAdapter.updateData(loginResponse);
            }
        }
    }

    @Override
    public void onHeartError() {
        mSmartRefreshLayout.finishRefresh();
        mVContent.setVisibility(View.GONE);
        mVError.setVisibility(View.VISIBLE);
        mVError.setOnClickListener(v -> {
            refresh();
            ToastUtil.showShort(getContext(), "正在刷新，请别急...", true);
        });
    }

    public class HeartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<HeartResponse> mHeartResponse;

        HeartAdapter(List<HeartResponse> mHeartResponse) {
            this.mHeartResponse = mHeartResponse;
        }

        /**
         * 更新数据
         */
        public void updateData(List<HeartResponse> heartResponseList) {
            this.mHeartResponse = heartResponseList;
            //   notifyItemInserted(0);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_heart_wall, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((ItemViewHolder) viewHolder).bindData(i);
        }

        @Override
        public int getItemCount() {
            return CommonUtils.size(mHeartResponse);
        }

        /**
         * 子项
         */
        class ItemViewHolder extends RecyclerView.ViewHolder {
            /**
             * 心声墙内容
             */
            private TextView tvContent;
            /**
             * 个性标签
             */
            private TextView tvLabel;
            /**
             * 头像
             */
            private RoundImageView ivHeartWallUserImage;
            /**
             * 性别
             */
            private ImageView ivSex;

            /**
             * 用户名  eg ：bbbb
             */
            private TextView tvUserName;

            ItemViewHolder(View itemView) {
                super(itemView);
                tvContent = itemView.findViewById(R.id.tv_content);
                ivHeartWallUserImage = itemView.findViewById(R.id.iv_heart_wall_user_image);
                // ivSex = itemView.findViewById(R.id.iv_sex);
                tvLabel = itemView.findViewById(R.id.tv_label);
                // tvUserName = itemView.findViewById(R.id.tv_username);
            }

            void bindData(final int position) {
                HeartResponse heartResponse = mHeartResponse.get(position);
                // 用户名
                //  tvUserName.setText(heartResponse.getUserName());
                // 用户头像
                Glide.with(getContext()).load(heartResponse.getImageUrl()).
                        placeholder(R.drawable.icon_default).into(ivHeartWallUserImage);

                //   // 性别
                //   String sex = heartResponse.getSex();
                //   if (StringUtils.isEqual(sex, MALE)) {
                //       CommonUtils.setViewBackground(ivSex, R.drawable.male);
                //   } else {
                //       CommonUtils.setViewBackground(ivSex, R.drawable.female);
                //   }

                // 个性标签
                tvLabel.setText(heartResponse.getLabel());

                // 内容
                tvContent.setText(heartResponse.getContent());

                // 没有更多了
                if (CommonUtils.size(mHeartResponse) > 2) {
                    if (position + 1 == CommonUtils.size(mHeartResponse)) {
                        itemView.findViewById(R.id.tv_bottom_now).setVisibility(View.VISIBLE);
                    } else {
                        itemView.findViewById(R.id.tv_bottom_now).setVisibility(View.GONE);
                    }
                } else {
                    itemView.findViewById(R.id.tv_bottom_now).setVisibility(View.GONE);
                }
                // 点击条目去详情页面
                itemView.setOnClickListener(v -> {
                    SingleHeartWallActivity.start(getContext(), heartResponse);
                });
            }
        }
    }

    /**
     * 发送心声成功的事件通知
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAssignSuc(AssignHeartToHeartFragmentEvent event) {
        ToastUtil.showShort(getContext(), event.getMessage(), true);
        mSmartRefreshLayout.autoRefresh(200);
    }
}