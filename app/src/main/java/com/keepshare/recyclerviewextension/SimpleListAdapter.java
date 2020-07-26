package com.keepshare.recyclerviewextension;

import android.content.Context;

import com.keepshare.extension.CommonAdapter;
import com.keepshare.extension.CommonViewHolder;

public class SimpleListAdapter extends CommonAdapter<String> {

    public SimpleListAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindView(CommonViewHolder<String> holder, String itemData, int position) {
        holder.setText(R.id.item_text_one, itemData);
        holder.setText(R.id.item_text_two, "测试+" + itemData);
    }

    @Override
    public int bindItemView(int viewType) {
        return R.layout.item_list;
    }
}
