package com.example.zt.graduate.main.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zt.graduate.R;
import com.example.zt.graduate.app.UserApplication;
import com.example.zt.graduate.main.chat.chat_message.ChatModel;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.PromptContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import lib_utils.CommonUtils;
import lib_utils.MyLogUtil;
import lib_utils.StringUtils;
import lib_utils.ToastUtil;
import lib_utils.db.entity.UserInfoTable;
import lib_utils.net.ji_guang.Const;
import mvp.BaseHeader;
import mvp.BaseMvpActivity;
import widget.RoundImageView;
import widget.header_widget.LeftIconHeader;
import widget.header_widget.LeftTextRightTextHeader;

/**
 * @author taozhu5
 * @date 2019/4/19 17:11
 * @description 聊天页面
 */
public class ChatMainActivity extends BaseMvpActivity {

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, ChatMainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 启动此页面
     *
     * @param context 上下文
     */
    public static void start(Context context, String userName, String imageUrl) {
        Intent intent = new Intent(context, ChatMainActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_IMAGE_URL, imageUrl);
        context.startActivity(intent);
    }

    private static final String EXTRA_USER_NAME = "extra_user_name";
    private static final String EXTRA_IMAGE_URL = "extra_image_url";

    private EditText mEtContent;
    private RecyclerView mRvChatMain;
    private ChatAdapter mChatAdapter;
    private String mUserName;
    private String mImageUrl;
    private String mContent;
    private List<ChatModel> list = new ArrayList<>();
    /**
     * 会话对象
     */
    private Conversation mConversation;

    /**
     * GreenDao相关操作
     */
    private UserApplication mUserApplication;
    private UserInfoTable mUserInfoTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册接收事件
        JMessageClient.registerEventReceiver(this);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_chat_main;
    }

    @Override
    public void initData() {
        mUserApplication = (UserApplication) getApplication();
        mUserInfoTable = mUserApplication.getUserInfoTable();
        Intent intent = getIntent();
        if (intent != null) {
            mUserName = intent.getStringExtra(EXTRA_USER_NAME);
            mImageUrl = intent.getStringExtra(EXTRA_IMAGE_URL);
        }
    }

    @Override
    public void initView() {
        LeftIconHeader leftTextRightTextHeader = $(R.id.header);
        leftTextRightTextHeader.getTvTitle().setText(getResources().getString(R.string.with_sb_chat, mUserName));
        leftTextRightTextHeader.setOnTitleClickListener(new BaseHeader.OnTitleClickListener() {
            @Override
            public void onLeftClick(View view) {
                onBackPressed();
            }

            @Override
            public void onRightClick(View view) {

            }
        });

        //  //进入会话状态,不接收通知栏
        //  JMessageClient.enterSingleConversation(userName);

        mRvChatMain = $(R.id.rv_chat_main);
        // if (mChatAdapter = null) {
        //     mChatAdapter = new ChatAdapter();
        // }
        mEtContent = $(R.id.et_content);
        $(R.id.tv_send_msg).setOnClickListener(v -> sendMsg());

        // list.add(new ChatModel(true, "你好，我是左边的"));
        // list.add(new ChatModel(false, "你好，我是右边的"));
        mChatAdapter = new ChatAdapter(list);
        mRvChatMain.setAdapter(mChatAdapter);
    }

    /**
     * 点击发送 发送消息
     */
    private void sendMsg() {
        if (StringUtils.isEmpty(mEtContent.getText().toString().trim())) {
            return;
        }

        // 发送消息之前，去创建一个会话
        // createConversation();
        String content = mEtContent.getText().toString().trim();

        // 和你点击认识一下的那个条目的对象创建会话
        mConversation = JMessageClient.getSingleConversation(mUserName, Const.App_Key);
        if (mConversation == null) {
            mConversation = Conversation.createSingleConversation(mUserName, Const.App_Key);
        }

        Message message = mConversation.createSendMessage(new TextContent(content));
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int status, String s) {
                if (status == 0) {
                    list.add(new ChatModel(false, content, mUserInfoTable.getImageUrl()));

                    mChatAdapter.update(list);
                    //  mChatAdapter.notifyDataSetChanged();
                    mEtContent.setText("");
                    // 消息发送成功
                    ToastUtil.showShort(ChatMainActivity.this, "发送成功" + s, true);
                } else {
                    // 消息发送失败
                    ToastUtil.showShort(ChatMainActivity.this, "发送成功" + s, false);
                }
            }
        });

        JMessageClient.sendMessage(message);
    }

    class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        /**
         * 会话消息集合
         */
        private List<ChatModel> list;
        private static final int MESSAGE_LEFT = 1;

        ChatAdapter(List<ChatModel> list) {
            this.list = list;
        }

        void update(List<ChatModel> list) {
            this.list = list;
            notifyDataSetChanged();
            //   mRvChatMain.scrollToPosition(getItemCount() - 1);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            // int viewType = getItemViewType(i);
            // FIXME　他妈的这个地方巨坑！这个地方的i是viewType  日
            if (i == MESSAGE_LEFT) {
                return new LeftMessageHolder(LayoutInflater.from(ChatMainActivity.this)
                        .inflate(R.layout.item_message_left, viewGroup, false));
            } else {
                return new RightMessageHolder(LayoutInflater.from(ChatMainActivity.this)
                        .inflate(R.layout.item_message_right, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            int viewType = getItemViewType(i);
            if (viewType == MESSAGE_LEFT) {
                LeftMessageHolder leftMessageHolder = (LeftMessageHolder) viewHolder;
                leftMessageHolder.bindData(i);
            } else {
                RightMessageHolder rightMessageHolder = (RightMessageHolder) viewHolder;
                rightMessageHolder.bindData(i);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).isLeft() ? MESSAGE_LEFT : -1;
        }

        @Override
        public int getItemCount() {
            return CommonUtils.size(list);
        }

        /**
         * 别人发的消息
         */
        class LeftMessageHolder extends RecyclerView.ViewHolder {

            private TextView tvMessage;
            private RoundImageView rvRoundImageView;

            private LeftMessageHolder(@NonNull View itemView) {
                super(itemView);
                tvMessage = itemView.findViewById(R.id.tv_message);
                rvRoundImageView = itemView.findViewById(R.id.iv_message);
            }

            void bindData(final int position) {
                Glide.with(_this()).load(mImageUrl).placeholder(getResources().
                        getDrawable(R.drawable.icon_default_people)).into(rvRoundImageView);
                tvMessage.setText(list.get(position).getMsg());
            }
        }

        /**
         * 我自己发的消息
         */
        class RightMessageHolder extends RecyclerView.ViewHolder {
            private TextView tvMessage;
            private RoundImageView rvRoundImageView;

            private RightMessageHolder(@NonNull View itemView) {
                super(itemView);
                tvMessage = itemView.findViewById(R.id.tv_message);
                rvRoundImageView = itemView.findViewById(R.id.iv_message);
            }

            void bindData(final int position) {
                Glide.with(_this()).load(mImageUrl).placeholder(getResources().
                        getDrawable(R.drawable.icon_default_people)).into(rvRoundImageView);
                tvMessage.setText(list.get(position).getMsg());
            }
        }

    }

    /**
     * TODO 这个方法的意义就是，1、别人发的消息都在这儿接收
     *
     * @param event
     */
    public void onEvent(MessageEvent event) {
        Message msg = event.getMessage();
        switch (msg.getContentType()) {
            case text:
                // 处理文字消息
                TextContent textContent = (TextContent) msg.getContent();
                mContent = textContent.getText();

                list.add(new ChatModel(true, mContent, mImageUrl));
                mChatAdapter.update(list);

                break;
            case image:
                //处理图片消息
                ImageContent imageContent = (ImageContent) msg.getContent();
                imageContent.getLocalPath();//图片本地地址
                imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                break;
            case voice:
                //处理语音消息
                VoiceContent voiceContent = (VoiceContent) msg.getContent();
                voiceContent.getLocalPath();//语音文件本地地址
                voiceContent.getDuration();//语音文件时长
                break;
            case custom:
                //处理自定义消息
                CustomContent customContent = (CustomContent) msg.getContent();
                customContent.getNumberValue("custom_num"); //获取自定义的值
                customContent.getBooleanValue("custom_boolean");
                customContent.getStringValue("custom_string");
                break;
            case eventNotification:
                //处理事件提醒消息
                EventNotificationContent eventNotificationContent = (EventNotificationContent) msg.getContent();
                switch (eventNotificationContent.getEventNotificationType()) {
                    case group_member_added:
                        //群成员加群事件
                        break;
                    case group_member_removed:
                        //群成员被踢事件
                        break;
                    case group_member_exit:
                        //群成员退群事件
                        break;
                    case group_info_updated://since 2.2.1
                        //群信息变更事件
                        break;
                    default:
                        break;
                }
                break;
            case unknown:
                // 处理未知消息，未知消息的Content为PromptContent
                // 默认提示文本为“当前版本不支持此类型消息，请更新sdk版本”
                // ，上层可选择不处理
                PromptContent promptContent = (PromptContent) msg.getContent();
                promptContent.getPromptType();//未知消息的type是unknown_msg_type
                break;
            default:
                break;
        }
    }

    // TODO 点击通知栏出现的消息通知做的事儿
    public void onEvent(NotificationClickEvent event) {
        list.add(new ChatModel(false, mContent));
        mChatAdapter.update(list);
        ToastUtil.showShort(ChatMainActivity.this, "点击了通知栏", true);

    }
}
