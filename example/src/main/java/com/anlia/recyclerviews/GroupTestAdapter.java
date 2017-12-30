package com.anlia.recyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anlia on 2017/12/29.
 */
public class GroupTestAdapter extends RecyclerView.Adapter<GroupTestAdapter.ViewHolder> {

    private Context context;
    private List<String> list;
    private OnItemClickListener mOnItemClickListener;

    class ViewHolder extends RecyclerView.ViewHolder {
        // TODO: 声明组件
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            // TODO: 注册组件,view.findViewById(R.id.xxx)
            textView = (TextView) view.findViewById(R.id.text_data);
        }

    }

    public GroupTestAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO: 为对应itemViewId赋值,例：R.layout.xxx
        int itemViewId = R.layout.item_test;
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(itemViewId, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // TODO: 绑定组件的事件
        holder.textView.setText(list.get(position));


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}