package com.anlia.library.header;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.anlia.library.group.GroupItem;

/**
 * Created by anlia on 2018/1/4.
 * 监听HeaderView的点击与长按事件，不适用于监听HeaderView的子View
 */

public class TopProjectionClickListener implements RecyclerView.OnItemTouchListener {
    TopProjectionDecoration mDecoration;
    OnTopProjectionClickListener mListener;
    GestureDetector mGestureDetector;

    public interface OnTopProjectionClickListener {
        void onTopProjectionClick(View headerView, int position);
        void onTopProjectionLongClick(View headerView, int position);
    }

    public TopProjectionClickListener(final TopProjectionDecoration decoration, OnTopProjectionClickListener listener){
        mDecoration = decoration;
        mListener = listener;
        mGestureDetector = new GestureDetector(decoration.getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                int position = decoration.findChildPositionUnder((int)e.getX(),(int)e.getY());
                if(position >=0 && mListener != null){
                    mListener.onTopProjectionClick(decoration.getHeaderView(),position);
                    return true;
                }else {
                    return false;
                }
            }

            @Override
            public void onLongPress(MotionEvent e) {
                int position = decoration.findChildPositionUnder((int)e.getX(),(int)e.getY());
                if(position >=0 && mListener != null){
                    mListener.onTopProjectionLongClick(decoration.getHeaderView(),position);
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if(mGestureDetector.onTouchEvent(e)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
