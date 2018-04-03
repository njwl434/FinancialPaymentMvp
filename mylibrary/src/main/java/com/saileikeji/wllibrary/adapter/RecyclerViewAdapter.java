package com.saileikeji.wllibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.saileikeji.wllibrary.util.LogUtil;

import java.util.List;

/**
 * Created by lqy on 2016/4/21.
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context context;
    protected List<T> list;
    protected View headerView;
    protected View footerView;
    protected View emptyView;
    protected boolean hasHeader = false;
    protected boolean hasFooter = false;
    public boolean needPosition = false;
    public boolean needShowEmpty = false;
    //    protected MyItemClickListener myItemClickListener;
    private OnItemClikListner onItemClikListner;

    public RecyclerViewAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    public RecyclerViewAdapter(Context context, List<T> list, View headerView) {
        this.context = context;
        this.list = list;
        setHeaderView(headerView);
    }

    public RecyclerViewAdapter(Context context, List<T> list, View headerView, View footerView) {
        this.context = context;
        this.list = list;
        setHeaderView(headerView);
        setFooterView(footerView);
    }

    public void setOnItemClikListner(OnItemClikListner onItemClikListner) {
        this.onItemClikListner = onItemClikListner;
    }

    public void setNeedPosition(boolean needPosition) {
        this.needPosition = needPosition;
    }

    public boolean isHeader(int position) {
        return hasHeader() && position == 0;
    }

    public boolean isFooter(int position) {
        if (hasHeader()) {
            return hasFooter() && position == list.size() + 1;
        } else {
            return hasFooter() && position == list.size();
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public View getHeaderView() {
        return headerView;
    }

    public View getFooterView() {
        return footerView;
    }

    public void setHeaderView(View headerView) {

        if (headerView != null) {
            if (!hasHeader()) {
                this.headerView = headerView;
                this.hasHeader = true;
                notifyItemInserted(0);
            } else {
                this.headerView = headerView;
                notifyDataSetChanged();
            }

        } else {
            if (hasHeader()) {
                this.hasHeader = false;
                notifyItemRemoved(0);
            }

        }

    }

    public void setFooterView(View footerView) {
        if (footerView != null) {
            if (!hasFooter()) {
                this.footerView = footerView;
                this.hasFooter = true;
                if (hasHeader()) {
                    notifyItemInserted(list.size() + 1);
                } else {
                    notifyItemInserted(list.size());
                }
            } else {
                this.footerView = footerView;
                notifyDataSetChanged();
            }

        } else {
            if (hasFooter()) {
                this.hasFooter = false;
                if (hasHeader()) {
                    notifyItemRemoved(list.size() + 1);
                } else {
                    notifyItemRemoved(list.size());
                }

            }

        }

    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public void setNeedShowEmpty(boolean needShowEmpty) {
        this.needShowEmpty = needShowEmpty;
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

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        count += (hasHeader() ? 1 : 0);
        count += (hasFooter() ? 1 : 0);

        count += list.size();
        if (list.size()==0 && needShowEmpty){
            count += (getEmptyView() == null) ? 0 : 1;
        }
        LogUtil.e(count+"");
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int size = list.size();
        if (hasHeader()) {
            if (position == 0) {
                return Item.TYPE_HEADER;
            } else {
                if (size == 0) {
                    return Item.TYPE_EMPTY;
                } else if (position == size + 1) {
                    return Item.TYPE_FOOTER;
                } else {

//                    return ((Item)list.get(position - 1)).getType();
                    return 2;
                }

            }

        } else {
            if (size == 0) {
                return Item.TYPE_EMPTY;
            } else if (position == size) {
                return Item.TYPE_FOOTER;
            } else {
//                return ((Item)list.get(position)).getType();
                return 2;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == Item.TYPE_HEADER) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            View headerView = headerHolder.itemView;

            onBindHeaderView(headerView);
        } else if (getItemViewType(position) == Item.TYPE_FOOTER) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            View footerView = footerHolder.itemView;

            onBindFooterView(footerView);
        } else if (getItemViewType(position) == Item.TYPE_EMPTY) {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            onBindEmptyView(emptyViewHolder.itemView);
//            emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        } else {
            int realPosition = position;
            if (hasHeader) {
                realPosition = position - 1;
            }
            T i = list.get(realPosition);
            final int finalRealPosition = realPosition;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClikListner != null) {
                        onItemClikListner.onItemClicked(list.get(finalRealPosition), finalRealPosition);
                    }
                }
            });
            if (needPosition) {
                onBindItemViewWithPosition(holder, i, realPosition);
            } else {
                onBindItemView(holder, i);
            }

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(myItemClickListener!=null)
//                    myItemClickListener.click(position);
//                }
//            });

        }

    }

    public  void onBindEmptyView(View itemView){};


    protected abstract void onBindHeaderView(View headerView);

    protected abstract void onBindFooterView(View footerView);

    protected abstract void onBindItemView(RecyclerView.ViewHolder holder, T T);

    public void onBindItemViewWithPosition(RecyclerView.ViewHolder holder, T t, int position) {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (hasHeader() && viewType == Item.TYPE_HEADER) {
            View v = getHeaderView();
            return new HeaderViewHolder(v);
        }
        if (hasFooter() && viewType == Item.TYPE_FOOTER) {
//			View v = LayoutInflater.from(parent.getContext()).inflate(getFooterView(), parent, false);
            View v = getFooterView();
            return new FooterViewHolder(v);
        } else if (viewType == Item.TYPE_EMPTY) {
            return new EmptyViewHolder(getEmptyView());
        } else {
            LogUtil.e("type == 2 ");
            return onCreateHolder(parent, viewType);
        }
    }

    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType);

    public interface Item {
        int TYPE_HEADER = 0;
        int TYPE_FOOTER = 1;
        int TYPE_EMPTY = 3;

        int getType();
    }

    //    public void setMyItemClickListener(MyItemClickListener l){
//        this.myItemClickListener = l;
//    }
//
//    public interface MyItemClickListener {
//        public void click(int position);
//    }
    public interface OnItemClikListner<T> {
        void onItemClicked(T t, int Position);
    }
}
