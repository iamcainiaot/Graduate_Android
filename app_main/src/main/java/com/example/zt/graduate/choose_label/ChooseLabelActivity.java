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

import com.example.zt.graduate.R;
import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.choose_label.iview.IChooseLabelView;
import com.example.zt.graduate.choose_label.model.LabelBean;
import com.example.zt.graduate.choose_label.model.response.ChooseLabelResponse;
import com.example.zt.graduate.choose_label.presenter.ChooseLabelPresenter;
import com.example.zt.graduate.db.greendaogen.DaoSession;
import com.example.zt.graduate.login.model.response.LoginResponse;
import com.example.zt.graduate.main.MainActivity;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import lib_utils.CommonUtils;
import lib_utils.MyLogUtil;
import lib_utils.ToastUtil;
import lib_utils.db.entity.UserInfoTable;
import lib_utils.net.tencent.TencentUserInfoModel;
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
public class ChooseLabelActivity extends BaseMvpActivity implements IChooseLabelView {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context, LoginResponse loginResponse) {
        Intent intent = new Intent(context, ChooseLabelActivity.class);
        intent.putExtra(EXTRA_LOGIN_RESPONSE, loginResponse);
        context.startActivity(intent);
    }

    /**
     * GreenDao相关操作
     */
    private UserApplication mUserApplication;
    private UserInfoTable mUserInfoTable;
    private DaoSession mDaoSession;

    private static final String EXTRA_LOGIN_RESPONSE = "extra_login_response";
    /**
     * 用来保存点击了标签多少次
     */
    private int clickCount = 0;
    /**
     * 选择的标签集合
     */
    private Map<Integer, String> mLabelList = new HashMap<>();

    /**
     * 点击去主页
     */
    private TextView mTvGo2MainActivity;

    /**
     * 适配器对象
     */
    private ChooseLabelAdapter mChooseLabelAdapter;

    /**
     * 选择标签Presenter
     */
    private ChooseLabelPresenter mChooseLabelPresenter;

    /**
     * qq登陆用户信息
     */
//    private TencentUserInfoModel mTencentUserInfoModel;
    private LoginResponse mLoginResponse;
    private boolean isMale = true;

    @Override
    public int layoutId() {
        return R.layout.activity_choose_label;
    }

    @Override
    public void initData() {
        mUserApplication = (UserApplication) getApplication();
        mUserInfoTable = mUserApplication.getUserInfoTable();
        mDaoSession = mUserApplication.getDaoSession();
        Intent intent = getIntent();
        if (intent != null) {
//            mTencentUserInfoModel = intent.getParcelableExtra(EXTRA_LOGIN_RESPONSE);
            mLoginResponse = intent.getParcelableExtra(EXTRA_LOGIN_RESPONSE);
        }
    }

    @Override
    public void initView() {
        SwitchButton switchButton = findViewById(R.id.tb_sex);
        switchButton.setOnCheckedChangeListener((view, isChecked) -> {
            isMale = !isMale;
        });

        mTvGo2MainActivity = $(R.id.tv_next_2_main_activity);
        mTvGo2MainActivity.setClickable(false);
        mTvGo2MainActivity.setOnClickListener(v -> {
            // 点击进入主页按钮
            clickGo2MainActivity();
        });

        // 波纹TextView
        TitanicTextView titanicTextView = findViewById(R.id.mt_text_view);
        RecyclerView rvChooseLabel = $(R.id.rv_choose_label);


        List<LabelBean> labelBeans = addList();
        if (mChooseLabelAdapter == null) {
            mChooseLabelAdapter = new ChooseLabelAdapter(labelBeans);
        }
        rvChooseLabel.setLayoutManager(new GridLayoutManager(ChooseLabelActivity.this, 3));
        // rvChooseLabel.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        // 设置间距
        rvChooseLabel.addItemDecoration(new MarginDecoration(10));
        rvChooseLabel.setAdapter(mChooseLabelAdapter);

        // 设置字体波纹上升
        titanicTextView.setTypeface(Typefaces.get(ChooseLabelActivity.this,
                "Satisfy-Regular.ttf"));
        new Titanic().start(titanicTextView);
    }

    /**
     * 点击进入主页
     */
    private void clickGo2MainActivity() {
        if (clickCount > 0) {
            Iterator it = mLabelList.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            int count = 0;
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getValue();
                sb.append(key.toString());
                if (count != mLabelList.size() - 1) {
                    sb.append(",");
                    count++;
                }
            }
            if (mChooseLabelPresenter == null || mChooseLabelPresenter.isDetached()) {
                mChooseLabelPresenter = new ChooseLabelPresenter(this, this);
            }

            String imageUrl = CommonUtils.getRandomImgUrl();
            // if (mTencentUserInfoModel != null) {
            //     mChooseLabelPresenter.doChooseLabel(sb.toString(), isMale, mTencentUserInfoModel.getFigureurl_2());
            // }
            mChooseLabelPresenter.doChooseLabel(mLoginResponse.getUserId(), sb.toString(), isMale, imageUrl);
        } else {
            ToastUtil.showShort(this, "请至少选择一个个性标签哦~", false);
        }
    }

    @Override
    public void onChooseLabelStart() {
        showProgress();
        MyLogUtil.d(getLocalClassName() + "开始请求");
    }

    @Override
    public void onChooseLabelReturned(List<ChooseLabelResponse> chooseLabelResponses) {
        if (chooseLabelResponses == null) {
            return;
        }
        ChooseLabelResponse chooseLabelResponse = chooseLabelResponses.get(0);

        if (!chooseLabelResponse.isOK()) {
            hideProgress();
            return;
        }
        // 设置密码为用户Id的后8位
        String password = chooseLabelResponse.getUserId().substring(24);
        JMessageClient.register(chooseLabelResponse.getUserName(), password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    hideProgress();
                    // userId userName sex label imageUrl
                    UserInfoTable userInfoTable = new UserInfoTable(chooseLabelResponse.getUserId(),
                            chooseLabelResponse.getUserName(), chooseLabelResponse.getSex(),
                            chooseLabelResponse.getLabel(), chooseLabelResponse.getImageUrl());
                    // 不同则需做操作
                    if (!userInfoTable.equals(mUserApplication.getUserInfoTable())) {
                        mUserApplication.getDaoSession().clear();
                        MyLogUtil.d("userInfoTable:" + userInfoTable.toString());
                        // 插入数据到表中
                        mDaoSession.insertOrReplace(userInfoTable);
                    }

                    MainActivity.start(ChooseLabelActivity.this);
                    finish();
                } else {
                    hideProgress();
                    ToastUtil.showShort(_this(), "注册用户名失败，请使用其他方式登陆！", false);
                }

            }
        });
        MyLogUtil.d(getLocalClassName() + "返回数据" + chooseLabelResponses);
    }

    public class ChooseLabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<LabelBean> list;
        private static final int HEADER = 1;
        private List<Integer> integerList = new ArrayList<>();


        private Map<Integer, Boolean> booleanMap;

        ChooseLabelAdapter(List<LabelBean> list) {
            this.list = list;
            setMap();
        }

        private void setMap() {
            booleanMap = new HashMap<>(16);
            booleanMap.put(CommonUtils.size(list), false);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == HEADER) {
                return new HeaderHolder(LayoutInflater.from(ChooseLabelActivity.this)
                        .inflate(R.layout.item_choose_label_header, parent, false));
            } else {
                return new ItemHolder(LayoutInflater.from(ChooseLabelActivity.this)
                        .inflate(R.layout.item_choose_label_normal, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            int viewType = getItemViewType(i);
            if (HEADER == viewType) {
                HeaderHolder headerHolder = (HeaderHolder) viewHolder;
                headerHolder.bindData(i);
            } else {
                ItemHolder itemHolder = (ItemHolder) viewHolder;
                itemHolder.bindData(i);
            }
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int i) {
                        int viewType = getItemViewType(i);
                        if (viewType == HEADER) {
                            return 3;
                        } else {
                            return 1;
                        }
                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            // 是头部
            if (list.get(position).isHeader()) {
                return HEADER;
            }
            return 0;
        }

        @Override
        public int getItemCount() {
            return CommonUtils.size(list);
        }

        class HeaderHolder extends RecyclerView.ViewHolder {
            /**
             * 显示的条目头部
             */
            private TextView tvHeader;

            public HeaderHolder(@NonNull View itemView) {
                super(itemView);
                tvHeader = itemView.findViewById(R.id.tv_header);
            }

            void bindData(final int position) {
                tvHeader.setText(list.get(position).getLabel());
            }
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

            public void bindData(final int position) {

                boolean isSelect = false;
                for (Integer integerList : integerList) {
                    if (position == integerList) {
                        isSelect = true;
                    }
                }

                if (isSelect) {
                    // 选择过了的
                    CommonUtils.setViewBackground(tvChooseLabel,
                            R.drawable.shape_choose_label_select);
                    tvChooseLabel.setText(list.get(position).getLabel());
                    tvChooseLabel.setTextColor(getResources().getColor(
                            R.color.white));
                } else {
                    // 没有选择过的
                    CommonUtils.setViewBackground(tvChooseLabel,
                            R.drawable.shape_choose_label_unselect);
                    tvChooseLabel.setText(list.get(position).getLabel());
                    tvChooseLabel.setTextColor(getResources().getColor(
                            R.color.color_font_main_grey));
                }

                // lambam 格式 快捷键 v + 回车
                itemView.setOnClickListener(v -> {
                    if (!isChoose) {
                        integerList.add(position);
                        CommonUtils.setViewBackground(tvChooseLabel,
                                R.drawable.shape_choose_label_select);
                        tvChooseLabel.setTextColor(getResources().getColor(
                                R.color.white));
                        booleanMap.put(position, true);
                        isChoose = true;
                        mLabelList.put(position, list.get(position).getLabel());
                        clickCount++;
                        mTvGo2MainActivity.setTextColor(getResources().getColor(R.color.common_purple));
                    } else {
                        CommonUtils.setViewBackground(tvChooseLabel,
                                R.drawable.shape_choose_label_unselect);
                        tvChooseLabel.setTextColor(getResources().getColor(
                                R.color.color_font_main_grey));
                        booleanMap.put(position, false);
                        isChoose = false;
                        clickCount--;
                        mLabelList.remove(position);
                        if (clickCount > 0) {
                            mTvGo2MainActivity.setTextColor(getResources().getColor(R.color.common_purple));
                        } else {
                            mTvGo2MainActivity.setTextColor(getResources().getColor(R.color.color_font_main_grey_2));
                        }
                    }
                });
            }
        }

    }

    private List<LabelBean> addList() {
        LabelBean labelBean = new LabelBean();
        List<LabelBean> labelBeans = new ArrayList<>();
        /**
         * 1.阅读：读书、读报、看小说;
         * 2.运动：篮球、足球;
         * 3.饮食：中餐、西餐;
         * 4.旅游：国内旅游、国外旅游、长途旅游、短途旅游;
         * 5.音乐：流行的、古典的、摇滚的、轻音乐的;
         * 6.饮茶：绿茶、红茶、乌龙茶、沱茶;
         * 7.影视：电影、连续剧、短剧、戏剧;
         * 8.书籍：文学、哲学、经济、政治;
         * 9.业余爱好：赋诗、书法、音乐、作画;
         * 10.服饰：运动、正式、休闲。
         */

        /**
         * 阅读
         */

        labelBeans.add(new LabelBean(true, "阅读~（我最爱读书，你咧？）"));
        labelBeans.add(new LabelBean(false, "读书"));
        labelBeans.add(new LabelBean(false, "看报"));
        labelBeans.add(new LabelBean(false, "看小说"));

        labelBeans.add(new LabelBean(true, "运动？我就喜欢运动"));
        labelBeans.add(new LabelBean(false, "球类"));
        labelBeans.add(new LabelBean(false, "跑步"));
        labelBeans.add(new LabelBean(false, "撸铁"));

        labelBeans.add(new LabelBean(true, "吃吃吃，我喜欢吃吃吃！"));
        labelBeans.add(new LabelBean(false, "中餐"));
        labelBeans.add(new LabelBean(false, "西餐"));
        labelBeans.add(new LabelBean(false, "川菜"));
        labelBeans.add(new LabelBean(false, "粤菜"));
        labelBeans.add(new LabelBean(false, "不辣的"));
        labelBeans.add(new LabelBean(false, "不放香菜的"));
        labelBeans.add(new LabelBean(false, "既不辣也不放香菜的"));
        labelBeans.add(new LabelBean(false, "安庆快餐"));

        labelBeans.add(new LabelBean(true, "旅游，我要去旅游！"));
        labelBeans.add(new LabelBean(false, "黄山"));
        labelBeans.add(new LabelBean(false, "五岳"));
        labelBeans.add(new LabelBean(false, "泰新印尼"));
        labelBeans.add(new LabelBean(false, "大蜀山"));

        labelBeans.add(new LabelBean(true, "我最爱听歌了，♫♬♪"));
        labelBeans.add(new LabelBean(false, "伦伦"));
        labelBeans.add(new LabelBean(false, "宏宏"));
        labelBeans.add(new LabelBean(false, "杰杰"));
        labelBeans.add(new LabelBean(false, "嵩嵩"));
        labelBeans.add(new LabelBean(false, "杰杰"));
        labelBeans.add(new LabelBean(false, "电鳗？"));
        labelBeans.add(new LabelBean(false, "比伯儿"));
        labelBeans.add(new LabelBean(false, "霉霉"));

        labelBeans.add(new LabelBean(true, "我是光荣的程序员，Hello World！"));
        labelBeans.add(new LabelBean(false, "Php是最好的语言"));
        labelBeans.add(new LabelBean(false, "Java"));
        labelBeans.add(new LabelBean(false, "Python"));
        labelBeans.add(new LabelBean(false, "C最快"));
        labelBeans.add(new LabelBean(false, "C++最恶心"));
        labelBeans.add(new LabelBean(false, "GO~"));
        labelBeans.add(new LabelBean(false, "Html语言（出门右转）"));

        return labelBeans;
    }
}