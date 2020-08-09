package com.keepshare.extension.listener;

import android.view.View;

import com.keepshare.extension.CommonAdapter;
import com.keepshare.extension.CommonViewHolder;

public interface OnItemClickListener<T> {

    void onItemClick(CommonViewHolder<T> viewHolder, View itemView,
                     CommonAdapter<T> adapter, int position, T itemData);
}
