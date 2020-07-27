package com.keepshare.intercept;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author：xiaoxin
 * @date：2020/7/27
 * @Copyright: keepshare, All rights reserved
 * @description：
 */

public class CleanConflictTouchListener extends RecyclerView.SimpleOnItemTouchListener {

    private static final float ACCURACY_VALUE = 0.5f;
    private static final int INVALID_POINTER = -1;
    private int mScrollPointerId = INVALID_POINTER;
    private int mInitialTouchX, mInitialTouchY;

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        final int actionIndex = motionEvent.getActionIndex();
        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mScrollPointerId = motionEvent.getPointerId(0);
                mInitialTouchX = (int) (motionEvent.getX() + ACCURACY_VALUE);
                mInitialTouchY = (int) (motionEvent.getY() + ACCURACY_VALUE);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mScrollPointerId = motionEvent.getPointerId(actionIndex);
                mInitialTouchX = (int) (motionEvent.getX() + ACCURACY_VALUE);
                mInitialTouchY = (int) (motionEvent.getY() + ACCURACY_VALUE);
                break;
            case MotionEvent.ACTION_MOVE:
                final int index = motionEvent.findPointerIndex(mScrollPointerId);
                if (index < 0) return false;
                final int scrollState = recyclerView.getScrollState();
                if (scrollState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    return false;
                }
                final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager == null) return false;
                final int scrollX = (int) (motionEvent.getX() + ACCURACY_VALUE) - mInitialTouchX;
                final int scrollY = (int) (motionEvent.getY() + ACCURACY_VALUE) - mInitialTouchY;
                if (layoutManager.canScrollHorizontally() && Math.abs(scrollX) > Math.abs(scrollY)) {
                    return true;
                }
                if (layoutManager.canScrollVertically() && Math.abs(scrollY) > Math.abs(scrollX)) {
                    return true;
                }
                break;
        }
        return false;
    }
}
