package com.keepshare.extension.listener;

public interface OnItemChildViewClickListener<T> {

    void onItemClick(int viewId, int position, T itemData);
}
