package com.saileikeji.ebangren.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.ui.base.BaseFragment;
import com.saileikeji.wllibrary.view.RoundImgView;
import com.saileikeji.wllibrary.view.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: Ebangren
 * @author: 武梁
 * @date: 2018/2/2 16:57
 * @mailbox: 1034905058@qq.com
 */

public class MeFragment extends BaseFragment {
    private final static String ARG_C = "centent";
    View rootView;
    @Bind(R.id.mTopBar)
    TopBar mTopBar;
    @Bind(R.id.touxiang)
    RoundImgView touxiang;
    @Bind(R.id.nicename)
    TextView nicename;
    @Bind(R.id.layone)
    RelativeLayout layone;
    @Bind(R.id.linviewa)
    LinearLayout linviewa;
    @Bind(R.id.laytwo)
    LinearLayout laytwo;
    @Bind(R.id.ImgaMe)
    ImageView ImgaMe;
    @Bind(R.id.imagebress)
    ImageView imagebress;
    @Bind(R.id.RelayShouquan)
    RelativeLayout RelayShouquan;
    @Bind(R.id.imagebressa)
    ImageView imagebressa;
    @Bind(R.id.RelayGouwuche)
    RelativeLayout RelayGouwuche;
    @Bind(R.id.RelayShoucang)
    LinearLayout RelayShoucang;
    @Bind(R.id.imagebressw)
    ImageView imagebressw;
    @Bind(R.id.Relayactivity)
    RelativeLayout Relayactivity;
    @Bind(R.id.imagebressb)
    ImageView imagebressb;
    @Bind(R.id.RelayYaoqing)
    RelativeLayout RelayYaoqing;
    @Bind(R.id.imagebressc)
    ImageView imagebressc;
    @Bind(R.id.RelayFabu)
    RelativeLayout RelayFabu;
    @Bind(R.id.imagebressd)
    ImageView imagebressd;
    @Bind(R.id.RelayDuihuan)
    RelativeLayout RelayDuihuan;
    @Bind(R.id.imagebresse)
    ImageView imagebresse;
    @Bind(R.id.RelayToushu)
    RelativeLayout RelayToushu;
    @Bind(R.id.imagebressea)
    ImageView imagebressea;
    @Bind(R.id.RelayKefu)
    RelativeLayout RelayKefu;

    public static MeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragmet_me;
    }

    @Override
    public void initView(View rootView) {
        this.rootView = rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
