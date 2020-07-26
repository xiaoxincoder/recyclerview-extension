package com.keepshare.extension.listener;

public interface OnItemViewClickListener<T> {

    void onItemClick(int viewId, int position, T itemData);
}
