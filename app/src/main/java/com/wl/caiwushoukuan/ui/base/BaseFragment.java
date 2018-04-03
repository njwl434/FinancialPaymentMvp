package com.wl.caiwushoukuan.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wl.caiwushoukuan.widgit.UIUtils;
import com.wl.wllibrary.view.LoadMoreView;
import com.wl.wllibrary.view.loadingview.AVLoadingIndicatorView;
import com.wl.wllibrary.view.loadingview.LoadingDialog;

import butterknife.ButterKnife;

/**
 * @describe: BaseFragment
 * @author: 武梁
 * @date: 2017/11/9 10:09
 * @mailbox: 1034905058@qq.com
 */

public abstract class BaseFragment extends Fragment {
//    public ApiManager apiManager;
    public AVLoadingIndicatorView loadingView;
    public LoadMoreView loadMoreView;
    public String token;
    public LoadingDialog mLoadingDialog;
    public String accessToken = "";
    public String userId = "";
//    public LoginInfo loginInfo;
    protected String TAG = this.getClass().getSimpleName();
//    List<AuthFormatBean> authorization = new ArrayList<>();
    public Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingView = new AVLoadingIndicatorView(getActivity());
        loadMoreView = new LoadMoreView(getActivity());
        mLoadingDialog = new LoadingDialog(getActivity());
//        apiManager = HttpUtil.getInstance(ApiManager.BASE_URL).getApiManager();
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        View rootView = inflater.inflate(provideContentViewId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }
    //    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
//        View rootView = inflater.inflate(provideContentViewId(), container, false);
//        ButterKnife.bind(this, rootView);
//        initView(rootView);
//        return rootView;
//    }

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    public abstract void initView(View rootView);
    public abstract class MessageBackReciver extends BroadcastReceiver
    {
        @Override
        public abstract void onReceive(Context context, Intent intent);
    }

    public void toast(String str) {
        UIUtils.showToastSafely(str);
    }}
