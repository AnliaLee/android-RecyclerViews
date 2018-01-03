package com.anlia.library.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anlia on 2018/1/3.
 * 简易的RecyclerView item点击监听，不适用于监听item的子View
 */

public class ItemClickListener implements RecyclerView.OnItemTouchListener {
    OnItemClickListener mListener;
    GestureDetector mGestureDetector;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public ItemClickListener(final RecyclerView parent, OnItemClickListener listener){
        mListener = listener;
        mGestureDetector = new GestureDetector(parent.getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = parent.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && mListener != null){
                    mListener.onItemClick(childView,parent.getChildLayoutPosition(childView));
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = parent.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mListener != null) {
                    mListener.onItemLongClick(childView,parent.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
}
