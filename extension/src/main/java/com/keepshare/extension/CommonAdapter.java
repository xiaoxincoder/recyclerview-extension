package com.keepshare.extension;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keepshare.extension.listener.OnItemClickListener;
import com.keepshare.extension.listener.OnItemChildViewClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder<T>> {

    private Context context;
    private List<T> dataList;
    private int limitCount = -1;
    private OnItemClickListener<T> onItemClickListener;
    private OnItemChildViewClickListener<T> onItemChildViewClickListener;

    public CommonAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void addDataList(List<T> dataList) {
        if (dataList == null || dataList.size() <= 0 )return;
        initListIfNeed();
        this.dataList.addAll(dataList);
        notifyItemRangeInserted(this.dataList.size(), dataList.size());
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void insertDataList(int startPosition, List<T> dataList) {
        if (dataList == null || dataList.size() <= 0) return;
        initListIfNeed();
        this.dataList.addAll(startPosition, dataList);
        notifyItemRangeInserted(startPosition, dataList.size());
    }

    public void insertItemData(int position, T itemData) {
        initListIfNeed();
        this.dataList.add(position, itemData);
        notifyItemInserted(position);
    }

    public void addItemData(T itemData) {
        initListIfNeed();
        this.dataList.add(itemData);
        notifyItemInserted(this.dataList.size());
    }

    public void removeItemData(T itemData) {
        initListIfNeed();
        int index = this.dataList.indexOf(itemData);
        this.dataList.remove(itemData);
        notifyItemRemoved(index);
    }

    public void removeItemData(int position) {
        initListIfNeed();
        if (this.dataList.size() >= position) return;
        this.dataList.remove(position);
        notifyItemRemoved(position);
    }

    private void initListIfNeed() {
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
    }

    public View getItemView(int position, RecyclerView parent) {
        if (parent == null) return null;
        RecyclerView.LayoutManager lm = parent.getLayoutManager();
        return lm != null ? lm.findViewByPosition(position) : null;
    }

    public T getItemData(int position) {
        initListIfNeed();
        return this.dataList != null && dataList.size() > position
                ? dataList.get(position) : null;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemChildViewClickListener(OnItemChildViewClickListener<T> onItemChildViewClickListener) {
        this.onItemChildViewClickListener = onItemChildViewClickListener;
    }

    @NonNull
    @Override
    public CommonViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommonViewHolder<T> viewHolder = new CommonViewHolder<>(context, LayoutInflater.from(context)
                .inflate(bindItemView(viewType), parent, false), this);
        viewHolder.setOnItemClickListener(onItemClickListener);
        viewHolder.setOnItemChildViewClickListener(onItemChildViewClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder<T> holder, int position) {
        final T itemData = getItemData(position);
        onBindView(holder, itemData, position);
    }

    @Override
    public int getItemCount() {
        initListIfNeed();
        if (limitCount < 0) {
            return dataList != null ? dataList.size() : 0;
        } else {
            return dataList != null ? Math.min(limitCount, dataList.size()) : 0;
        }
    }

    public abstract void onBindView(CommonViewHolder<T> holder, T itemData, int position);

    public abstract int bindItemView(int viewType);

}
