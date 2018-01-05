package com.anlia.recyclerviews.group;

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
import com.anlia.library.group.GroupItem;
import com.anlia.library.group.GroupItemClickListener;
import com.anlia.library.group.SideGroupItemDecoration;
import com.anlia.recyclerviews.GroupTestAdapter;
import com.anlia.recyclerviews.R;

import java.util.ArrayList;
import java.util.List;

public class SideGroupActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    private GroupTestAdapter groupTestAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_group);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData(){
        for (int i=0;i<30;i++){
            list.add(i+1+"");
        }

        groupTestAdapter = new GroupTestAdapter(this,list);
        recyclerView.setAdapter(groupTestAdapter);

        //开始使用GroupItemDecoration
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.item_group_side,null);
        SideGroupItemDecoration groupItemDecoration = new SideGroupItemDecoration(this, view, new SideGroupItemDecoration.DecorationCallback() {
            @Override
            public void setGroup(List<GroupItem> groupList) {
                //设置分组，例如：
                GroupItem groupItem;
                int startPosition = 0;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).equals("10")){
                        groupItem = new GroupItem(0);
                        groupItem.setData("name","1-10");
                        groupList.add(groupItem);
                        startPosition=i+1;
                    }else if(list.get(i).equals("20")){
                        groupItem = new GroupItem(startPosition);
                        groupItem.setData("name","11-20");
                        groupItem.setData("imgId",R.drawable.ic_head);
                        groupList.add(groupItem);
                        startPosition=i+1;
                    }else if(list.get(i).equals("30")){
                        groupItem = new GroupItem(startPosition);
                        groupItem.setData("name","21-30");
                        groupList.add(groupItem);
                    }
                }
            }

            @Override
            public void buildGroupView(View groupView, GroupItem groupItem) {
                //构建GroupView，通过view.findViewById找到内部控件，例如
                TextView textName = (TextView) groupView.findViewById(R.id.text_name);
                textName.setText(groupItem.getData("name").toString());
            }
        });
        recyclerView.addItemDecoration(groupItemDecoration);
        recyclerView.addOnItemTouchListener(new GroupItemClickListener(groupItemDecoration,new GroupItemClickListener.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(GroupItem groupItem) {
                Toast.makeText(SideGroupActivity.this, "点击了Group:"+groupItem.getData("name"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGroupItemLongClick(GroupItem groupItem) {
                Toast.makeText(SideGroupActivity.this, "长按了Group:"+groupItem.getData("name"), Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
