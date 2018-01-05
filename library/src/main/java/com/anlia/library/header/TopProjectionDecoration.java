package com.anlia.library.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anlia on 2018/1/4.
 */

public class TopProjectionDecoration extends RecyclerView.ItemDecoration {
    private View headerView;
    private Context context;
    private DecorationCallback decorationCallback;

    private boolean isFirst = true;
    private int childViewHeight;
    private int headerViewHeight = 0;
    private int headerViewWidth = 0;
    private int headerPosition = 0;
    private boolean isProjectionChange = true;

    public TopProjectionDecoration(Context context, View headerView, DecorationCallback decorationCallback){
        this.context = context;
        this.headerView = headerView;
        this.decorationCallback = decorationCallback;
    }

    /**
     * 顶部投影是否随列表滑动而改变，默认为true
     * @param b
     */
    public void isProjectionChange(boolean b){
        isProjectionChange = b;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(isFirst){
            measureView(view,parent);
            childViewHeight = view.getHeight();

            measureView(headerView,parent);
            headerViewHeight = headerView.getMeasuredHeight();
            headerViewWidth = headerView.getMeasuredWidth();
            isFirst = false;
        }

        if(headerViewHeight - childViewHeight < 0){
            return;
        }
        int position = parent.getChildAdapterPosition(view);
        if(position == 0){
            outRect.top = headerViewHeight - childViewHeight;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            float bottom = child.getBottom();
            if(bottom <= headerViewHeight){
                if(isProjectionChange){
                    headerPosition = parent.getChildAdapterPosition(child);
                }
            }
        }

        c.save();
        c.translate(0,0);
        decorationCallback.buildHeaderView(headerView,headerPosition);
        drawHeaderView(c,parent);
        c.restore();
    }

    private void drawHeaderView(Canvas canvas, RecyclerView parent){
        measureView(headerView,parent);
        headerView.draw(canvas);
    }

    public interface DecorationCallback {
        /**
         * 构建HeaderView
         * @param headerView
         * @param position
         */
        void buildHeaderView(View headerView, int position);
    }

    public int findChildPositionUnder(int x, int y){
        Rect rect = new Rect(0,0,headerViewWidth,headerViewHeight);
        if(rect.contains(x,y)){
            return headerPosition;
        }else {
            return -1;
        }
    }

    public Context getContext(){
        return context;
    }

    public View getHeaderView(){
        return headerView;
    }

    /**
     * 测量View的大小和位置
     * @param view
     * @param parent
     */
    private void measureView(View view,View parent){
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(), view.getLayoutParams().width);

        int childHeight;
        if(view.getLayoutParams().height > 0){
            childHeight = View.MeasureSpec.makeMeasureSpec(view.getLayoutParams().height, View.MeasureSpec.EXACTLY);
        } else {
            childHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);//未指定
        }

        view.measure(childWidth, childHeight);
        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
    }
}
