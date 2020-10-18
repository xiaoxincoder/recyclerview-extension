package com.keepshare.extension;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CommonAdapterDataObserver extends RecyclerView.AdapterDataObserver {

    private View emptyView;
    private int extraViewNum;
    private boolean startCheck;
    private RecyclerView recyclerView;

    public void attachToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        checkIfEmpty();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);
        checkIfEmpty();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
        super.onItemRangeChanged(positionStart, itemCount, payload);
        checkIfEmpty();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        checkIfEmpty();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        super.onItemRangeRemoved(positionStart, itemCount);
        checkIfEmpty();
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        checkIfEmpty();
    }

    public void setStartCheck(boolean startCheck) {
        this.startCheck = startCheck;
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView, int otherCount) {
        this.emptyView = emptyView;
        this.extraViewNum = otherCount;
        checkIfEmpty();
    }

    public void setExtraViewNum(int extraViewNum) {
        this.extraViewNum = extraViewNum;
        checkIfEmpty();
    }

    private void checkIfEmpty() {
        if (emptyView != null && recyclerView != null
                &&  recyclerView.getAdapter() != null && startCheck) {
            final int itemCount = recyclerView.getAdapter().getItemCount() - extraViewNum;
            final boolean emptyViewVisible = itemCount == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            recyclerView.setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }
}
