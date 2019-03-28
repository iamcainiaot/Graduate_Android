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
import widget.TitanicTextView.Titanic;
import widget.TitanicTextView.TitanicTextView;
import widget.TitanicTextView.Typefaces;
import widget.recyclerview.MarginDecoration;

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
     * 占据GridLayout的Item子项个数  分别是 一个Item是5个字符，两个Item是10个字符 三个Item是15个字符
     */
    private static final int PLACE_ONE_ITEM_CHARS = 10;
    private static final int PLACE_TWO_ITEM_CHARS = 20;

    /**
     * 波纹TextView
     */
    private TitanicTextView mTitanicTextView;
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
        mTitanicTextView = findViewById(R.id.mt_text_view);
        RecyclerView rvChooseLabel = $(R.id.rv_choose_label);
        List<String> list = addList();
        if (mChooseLabelAdapter == null) {
            mChooseLabelAdapter = new ChooseLabelAdapter(list);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                // 返回值是值每个Item占用的个数，这个个数是我们上面设置的 3
                int sLength = list.get(i).length();
                if (sLength < PLACE_ONE_ITEM_CHARS) {
                    return 1;
                } else if (PLACE_ONE_ITEM_CHARS <= sLength && sLength < PLACE_TWO_ITEM_CHARS) {
                    return 2;
                } else {
                    return 3;
                }
            }
        });
        rvChooseLabel.setLayoutManager(gridLayoutManager);
        // 设置间距
        rvChooseLabel.addItemDecoration(new MarginDecoration(10));
        rvChooseLabel.setAdapter(mChooseLabelAdapter);
        // 设置字体波纹上升
        mTitanicTextView.setTypeface(Typefaces.get(ChooseLabelActivity.this,
                "Satisfy-Regular.ttf"));
        new Titanic().start(mTitanicTextView);
    }

    public class ChooseLabelAdapter extends RecyclerView.Adapter<ChooseLabelAdapter.ItemHolder> {

        private List<String> list;
        private String isNull = "1";

        ChooseLabelAdapter(List<String> list) {
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

            /**
             * 是否选择过了
             */
            private boolean isChoose = false;

            private ItemHolder(View itemView) {
                super(itemView);
                tvChooseLabel = itemView.findViewById(R.id.tv_choose_label);
            }

            private void bindData(final int position) {
                if (isNull.equals(list.get(position))) {
                    // 设置中间的空白
                    tvChooseLabel.setVisibility(View.INVISIBLE);
                } else {
                    tvChooseLabel.setText(list.get(position));
                    // lambam 格式 快捷键 v + 回车
                    itemView.setOnClickListener(v -> {
                        if (!isChoose) {
                            CommonUtils.setViewBackground(tvChooseLabel,
                                    R.drawable.shape_choose_label_select);
                            tvChooseLabel.setTextColor(getResources().getColor(
                                    R.color.common_purple));
                            isChoose = true;
                        } else {
                            CommonUtils.setViewBackground(tvChooseLabel,
                                    R.drawable.shape_choose_label_unselect);
                            tvChooseLabel.setTextColor(getResources().getColor(
                                    R.color.color_font_main_grey));
                            isChoose = false;
                        }
                    });
                }
            }
        }
    }

    private List<String> addList() {
        List<String> list = new ArrayList<>();
        /**
         * 添加数据的时候需注意，满足一行三个数的规则，否则不好看
         */
        /**
         * 电影
         */
        list.add("毒液");
        list.add("指环王");
        list.add("飞驰人生");
        list.add("Tom And Jerry");
        list.add("毒液");
        list.add("毒液");
        list.add("指环王");
        list.add("飞驰人生");
        list.add("Tom And Jerry");
        list.add("毒液");
        list.add("1");
        list.add("1");
        list.add("1");
        /**
         * IT
         */
        list.add("CSDN");
        list.add("Eclipse");
        list.add("Android");
        list.add("1");
        list.add("1");
        list.add("1");
        /**
         * 运动
         */
        list.add("篮球");
        list.add("足球！");
        list.add("乒乓球");
        list.add("排球！");
        list.add("羽毛球！");
        list.add("网球！");
        list.add("铅球！");
        return list;
    }
}
