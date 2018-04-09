package com.saileikeji.ebangren.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.TestInfo;
import com.saileikeji.ebangren.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @describe: 课程分类
 * @author: 武梁
 * @date: 2018/4/9 09:29
 * @mailbox: 1034905058@qq.com
 */

public class CourseTypeActivity extends BaseActivity {

    @Bind(R.id.mlaylout)
    LinearLayout mlaylout;
    @Bind(R.id.ImageSearch)
    ImageView ImageSearch;
    @Bind(R.id.ImageSearchx)
    ImageView ImageSearchx;
    @Bind(R.id.editSearch)
    TextView editSearch;
    @Bind(R.id.etlay)
    RelativeLayout etlay;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.a_recycle_coursetype)
    RecyclerView aRecycleCoursetype;
    private List<Map<String, Object>> dataList;
    CourseTypeAdapte adapte;
    List<TestInfo> testInfoList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursetype);
        ButterKnife.bind(this);
        testInfoList.clear();
        for (int i = 0; i < 6; i++) {
            TestInfo in = new TestInfo();
            in.setName("测试" + 1);
            testInfoList.add(in);
        }
        adapte=new CourseTypeAdapte();
        aRecycleCoursetype.setLayoutManager(new LinearLayoutManager(aRecycleCoursetype.getContext()));
        aRecycleCoursetype.setAdapter(adapte);
        adapte.setNewData(testInfoList);


    }
    public class CourseTypeAdapte extends BaseQuickAdapter<TestInfo, BaseViewHolder> {
        SimpleAdapter adapter;
        public CourseTypeAdapte() {
            super(R.layout.item_course_type, null);
        }

        @Override
        protected void convert(BaseViewHolder helper, TestInfo item) {
            helper.setText(R.id.item_tv_tv, item.getName());
            GridView gridView =helper.getView(R.id.item_gridview);
            String name[]={"时钟","信号","宝箱","秒钟","大象","FF","记事本","书签","印象","商店","主题","迅雷"};
            adapter=new SimpleAdapter(CourseTypeActivity.this, dataList, R.layout.gridview_item, name, null);
            gridView.setAdapter(adapter);
//        helper.setImageResource(R.id.ImgItemHome, R.mipmap.ic_zhanwei);
//        ImageView imge = helper.getView(R.id.ImgItemHome);
//        helper.setText(R.id.quanhoutv, item.getzkFinalPrice());
//        helper.setText(R.id.quantva, item.getVolume());
//        helper.setText(R.id.itemServiceLogTvBtn, item.getCouponInfo());
//        helper.addOnClickListener(R.id.shouchangImg);
//        if (item.getIsColle().equals("1")) {
//            helper.setImageResource(R.id.shouchangImg, R.drawable.home_collection_r);
//        } else {
//            helper.setImageResource(R.id.shouchangImg, R.drawable.home_collection_g);
//        }
//        Glide.with(getActivity())
//                .load(item.getPictUrl())
//                .placeholder(R.mipmap.default_error)
//                .error(R.mipmap.default_error)
//                .centerCrop()
//                .crossFade()
//                .into(imge);
//    }
        }}
}
