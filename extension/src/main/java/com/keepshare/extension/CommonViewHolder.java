package com.keepshare.extension;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keepshare.extension.listener.OnItemClickListener;
import com.keepshare.extension.listener.OnItemChildViewClickListener;

public class CommonViewHolder<T> extends RecyclerView.ViewHolder {

    private Context tempContext;
    private CommonAdapter<T> mAdapter;
    private SparseArray<View> cacheViewList;
    private OnClickCallback<T> onClickCallback;
    private OnItemClickListener<T> onItemClickListener;
    private OnChildClickCallback<T> onChildClickListener;
    private OnItemChildViewClickListener<T> onItemChildViewClickListener;

    public CommonViewHolder(Context context, @NonNull View itemView, CommonAdapter<T> adapter) {
        super(itemView);
        this.mAdapter = adapter;
        this.tempContext = context;
        cacheViewList = new SparseArray<>();
        bindItemViewClickEvent();
        bindItemChildViewClickEvent(itemView);
    }

    private void bindItemViewClickEvent() {
        if (onItemClickListener != null) {
            if (onClickCallback == null) {
                onClickCallback = new OnClickCallback<T>(mAdapter, onItemClickListener);
                onClickCallback.attachToViewHolder(this);
            }
            itemView.setOnClickListener(onClickCallback);
        }
    }

    private void bindItemChildViewClickEvent(View itemView) {
        if (onItemChildViewClickListener != null) {
            if (onChildClickListener == null) {
                onChildClickListener = new OnChildClickCallback<>(mAdapter, onItemChildViewClickListener);
                onChildClickListener.attachToViewHolder(this);
            }
            if (itemView instanceof ViewGroup) {
                int childCount = ((ViewGroup) itemView).getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = ((ViewGroup) itemView).getChildAt(i);
                    int viewId = child.getId();
                    if (viewId <= 0) continue;
                    child.setOnClickListener(onChildClickListener);
                }
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemChildViewClickListener(OnItemChildViewClickListener<T> onItemChildViewClickListener) {
        this.onItemChildViewClickListener = onItemChildViewClickListener;
    }

    public <V extends View> V getView(int id) {
        View view = cacheViewList.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            cacheViewList.put(id, view);
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

    public static class OnClickCallback<T> implements View.OnClickListener {

        private CommonAdapter<T> mAdapter;
        private CommonViewHolder<T> viewHolder;
        private OnItemClickListener<T> onItemClickListener;

        public OnClickCallback(CommonAdapter<T> mAdapter, OnItemClickListener<T> onItemClickListener) {
            this.mAdapter = mAdapter;
            this.onItemClickListener = onItemClickListener;
        }

        public void attachToViewHolder(CommonViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null && mAdapter != null) {
                final int position = viewHolder.getAdapterPosition();
                onItemClickListener.onItemClick(position, mAdapter.getItemData(position));
            }
        }
    }


    public static class OnChildClickCallback<T> implements View.OnClickListener {

        private CommonAdapter<T> mAdapter;
        private CommonViewHolder<T> viewHolder;
        private OnItemChildViewClickListener<T> onItemClickListener;

        public OnChildClickCallback(CommonAdapter<T> mAdapter, OnItemChildViewClickListener<T> onItemClickListener) {
            this.mAdapter = mAdapter;
            this.onItemClickListener = onItemClickListener;
        }

        public void attachToViewHolder(CommonViewHolder<T> viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            if (mAdapter != null && viewHolder != null && onItemClickListener != null) {
                final int position = viewHolder.getAdapterPosition();
                onItemClickListener.onItemClick(v.getId(),
                        position, mAdapter.getItemData(position));
            }
        }
    }
}
