package com.example.zt.graduate.choose_label;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.graduate_android.R;

import java.util.ArrayList;
import java.util.List;

import lib_utils.CommonUtils;
import mvp.BaseMvpActivity;

/**
 * @author taozhu5
 * @date 2019/3/15 14:43
 * @description 标签选择类
 */
public class ChooseLabelActivity extends BaseMvpActivity {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, ChooseLabelActivity.class);
        context.startActivity(intent);
    }

    /**
     * 适配器对象
     */
    private ChooseLabelAdapter mChooseLabelAdapter;

    @Override
    public int layoutId() {
        return R.layout.activity_choose_label;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        RecyclerView rvChooseLabel = $(R.id.rv_choose_label);
        List<String> list = new ArrayList();
        int size = CommonUtils.size(list);
        for (int i = 0; i < 10; i++) {
            list.add("hellorworld" + i);
        }
        if (mChooseLabelAdapter == null) {
            mChooseLabelAdapter = new ChooseLabelAdapter(list);
        }
        rvChooseLabel.setLayoutManager(new GridLayoutManager(this, 3));
        rvChooseLabel.setAdapter(mChooseLabelAdapter);
    }

    class ChooseLabelAdapter extends RecyclerView.Adapter<ChooseLabelAdapter.ItemHolder> {
        private List<String> list = new ArrayList();

        ChooseLabelAdapter(List list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemHolder(LayoutInflater.from(ChooseLabelActivity.this).inflate(R.layout.item_choose_label, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return CommonUtils.size(list);
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            /**
             * 标签内容
             */
            private TextView tvChooseLabel;

            private ItemHolder(View itemView) {
                super(itemView);
                tvChooseLabel = itemView.findViewById(R.id.tv_choose_label);
            }

            private void bindData(final int position) {
                tvChooseLabel.setText(list.get(position));
            }
        }
    }
}
