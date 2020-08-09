package com.keepshare.extension.listener;

import android.view.View;

import com.keepshare.extension.CommonAdapter;
import com.keepshare.extension.CommonViewHolder;

public interface OnItemChildViewClickListener<T> {

    void onItemClick(CommonViewHolder<T> viewHolder, CommonAdapter<T> adapter,
                     View childView, int position, T itemData);
}
