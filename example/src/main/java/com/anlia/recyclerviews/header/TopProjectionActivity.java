package com.anlia.recyclerviews.header;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anlia.library.base.ItemClickListener;
import com.anlia.library.header.TopProjectionClickListener;
import com.anlia.library.header.TopProjectionDecoration;
import com.anlia.recyclerviews.GroupTestAdapter;
import com.anlia.recyclerviews.R;

import java.util.ArrayList;
import java.util.List;

public class TopProjectionActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    private GroupTestAdapter groupTestAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_projection);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData(){
        for (int i=0;i<30;i++){
            list.add(i+1+"");
        }

        groupTestAdapter = new GroupTestAdapter(this,list);
        groupTestAdapter.setOnItemClickListener(new GroupTestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("test","is:"+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.e("test","il:"+position);
            }
        });
        recyclerView.setAdapter(groupTestAdapter);

        //开始使用GroupItemDecoration
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.item_top,null);
        TopProjectionDecoration decoration = new TopProjectionDecoration(this, view, new TopProjectionDecoration.DecorationCallback() {
            @Override
            public void buildHeaderView(View headerView, int position) {
                TextView textView = (TextView) headerView.findViewById(R.id.text_name);
                textView.setText(list.get(position));
            }
        });
//        decoration.isProjectionChange(false);//顶部投影是否随列表滑动而改变
        recyclerView.addItemDecoration(decoration);
        recyclerView.addOnItemTouchListener(new TopProjectionClickListener(decoration, new TopProjectionClickListener.OnTopProjectionClickListener() {
            @Override
            public void onTopProjectionClick(View headerView, int position) {
                Log.e("test","hs:"+position);
            }

            @Override
            public void onTopProjectionLongClick(View headerView, int position) {
                Log.e("test","hl:"+position);
            }
        }));
    }
}
