package com.saileikeji.ebangren.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.saileikeji.ebangren.R;
import com.saileikeji.ebangren.bean.QuanziBean;

import java.util.List;
import java.util.Map;

public class GridViewCommunityAdapter extends BaseAdapter implements ListAdapter {

	private List<QuanziBean> mList;
	private Context mContex;

	public GridViewCommunityAdapter(Context context, List<QuanziBean> list) {
		this.mContex = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = View.inflate(mContex, R.layout.item_gridview_community, null);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.mImgtitle);
			viewHolder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			viewHolder.mTvMessage = (TextView) convertView.findViewById(R.id.mTvMessage);

			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		QuanziBean map = mList.get(position);
		Glide.with(mContex)
				.load(map.getImages())
				.placeholder(R.mipmap.default_error)
				.error(R.mipmap.default_error)
				.centerCrop()
				.crossFade()
				.into(viewHolder.image);
		viewHolder.mTvMessage.setText((String) map.getMessage());
		return convertView;
	}
	
	class ViewHolder{
		ImageView image;
		TextView mTvTitle,mTvMessage;
		TextView desc;
	}
}
