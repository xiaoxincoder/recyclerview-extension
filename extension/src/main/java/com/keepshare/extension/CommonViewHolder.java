package com.keepshare.extension;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommonViewHolder<T> extends RecyclerView.ViewHolder {

    private Context tempContext;
    private CommonAdapter<T> mAdapter;
    private SparseArray<View> itemViews;

    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public <V extends View> V getView(int id) {
        View view = itemViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            itemViews.put(id, view);
        }
        return (V) view;
    }

    public View findView(int viewId) {
        return itemView.findViewById(viewId);
    }

    public ImageView getImageView(int viewId) {
        return getView(viewId);
    }

    public CommonViewHolder<T> setText(int viewId, String value) {
        TextView view = getView(viewId);
        view.setText(value);
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setEnable(int viewId, boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(tempContext.getResources().getColor(textColor));
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setTextSize(int viewId, int textSize) {
        TextView view = getView(viewId);
        view.setTextSize(textSize);
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(tempContext.getResources().getColor(color));
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setImageBitmap(@IdRes int viewId, Bitmap bm){
        View view = getView(viewId);
        ((ImageView)view).setImageBitmap(bm);
        return CommonViewHolder.this;
    }


    public CommonViewHolder<T> setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return CommonViewHolder.this;
    }

    public CommonViewHolder<T> setInvisible(int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.INVISIBLE);
        return CommonViewHolder.this;
    }

}
