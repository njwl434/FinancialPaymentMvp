package com.saileikeji.wllibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lqy on 16/10/29.
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * 用于存放分组的recycleradapter 目前支持三级 GRAND,PARENT,CHILD
 */

public abstract class SectionAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SectionItem> sectionItems;
    private View headView;
    private View footView;
    private boolean hasHead = false;
    private boolean hasFoot = false;

    public SectionAdapter(Context context, List<SectionItem> commentItems, View headView, View footerView) {
        this.context = context;
        this.sectionItems = commentItems;
        this.headView = headView;
        this.footView = footerView;
        setHeaderView(headView);
        setFooterView(footerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (hasHead && viewType == RecyclerViewAdapter.Item.TYPE_HEADER) {
            View v = headView;
            return new HeaderViewHolder(v);
        } else if (hasFoot && viewType == RecyclerViewAdapter.Item.TYPE_FOOTER) {
//			View v = LayoutInflater.from(parent.getContext()).inflate(getFooterView(), parent, false);
            View v = footView;
            return new FooterViewHolder(v);
        } else if (viewType == SectionItem.TYPE_GRAND) {
            return onCreateGrandViewHoler();
        } else if (viewType == SectionItem.TYPE_PARENT) {
            return onCreateParentViewHoler();
        } else if (viewType == SectionItem.TYPE_CHILD) {
            return onCreateChildViewHoler();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == RecyclerViewAdapter.Item.TYPE_HEADER) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            View headerView = headerHolder.itemView;

            onBindHeaderView(headerView);
        } else if (getItemViewType(position) == RecyclerViewAdapter.Item.TYPE_FOOTER) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            View footerView = footerHolder.itemView;

            onBindFooterView(footerView);
        } else {
            int realPosition = position;
            if (hasHead) {
                realPosition = position - 1;
            }
            SectionItem i = sectionItems.get(realPosition);
            if (i.getType() == SectionItem.TYPE_GRAND)
                onBindGrandViewWithPosition(holder, i, realPosition);
            if (i.getType() == SectionItem.TYPE_PARENT)
                onBindParentViewWithPosition(holder, i, realPosition);
            if (i.getType() == SectionItem.TYPE_CHILD)
                onBindChildViewWithPosition(holder, i, realPosition);
        }

    }


    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setHeaderView(View headerView) {

        if (headerView != null) {
            if (!hasHead) {
                this.hasHead = true;
                notifyItemInserted(0);
            } else {
                notifyDataSetChanged();
            }

        } else {
            if (hasHead) {
                this.hasHead = false;
                notifyItemRemoved(0);
            }

        }

    }

    public void setFooterView(View footerView) {
        if (footerView != null) {
            if (!hasFoot) {
                this.hasFoot = true;
                if (hasHead) {
                    notifyItemInserted(sectionItems.size() + 1);
                } else {
                    notifyItemInserted(sectionItems.size());
                }
            } else {
                notifyDataSetChanged();
            }

        } else {
            if (hasFoot) {
                this.hasFoot = false;
                if (hasHead) {
                    notifyItemRemoved(sectionItems.size() + 1);
                } else {
                    notifyItemRemoved(sectionItems.size());
                }

            }

        }

    }

    @Override
    public int getItemCount() {
        if (hasHead) {
            if (hasFoot) {
                return sectionItems.size() + 2;
            }
            return sectionItems.size() + 1;
        } else if (hasFoot) {
            return sectionItems.size() + 1;
        }
        return sectionItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        int size = sectionItems.size();
        if (hasHead) {
            if (position == 0) {
                return RecyclerViewAdapter.Item.TYPE_HEADER;
            } else {
                if (position == size + 1) {
                    return RecyclerViewAdapter.Item.TYPE_FOOTER;
                } else {
                    return sectionItems.get(position - 1).getType();
                }

            }

        } else {
            if (position == size) {
                return RecyclerViewAdapter.Item.TYPE_FOOTER;
            } else {
                return sectionItems.get(position).getType();
            }
        }
    }

    protected abstract void onBindHeaderView(View headerView);

    protected abstract void onBindFooterView(View footerView);

    public abstract RecyclerView.ViewHolder onCreateGrandViewHoler();

    public abstract RecyclerView.ViewHolder onCreateParentViewHoler();

    public abstract RecyclerView.ViewHolder onCreateChildViewHoler();

    protected abstract void onBindGrandViewWithPosition(RecyclerView.ViewHolder holder, SectionItem i, int realPosition);

    protected abstract void onBindParentViewWithPosition(RecyclerView.ViewHolder holder, SectionItem i, int realPosition);

    protected abstract void onBindChildViewWithPosition(RecyclerView.ViewHolder holder, SectionItem i, int realPosition);

}
