package com.anlia.recyclerviews;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anlia.library.base.ItemClickListener;
import com.anlia.library.group.GroupItem;
import com.anlia.library.group.GroupItemClickListener;
import com.anlia.library.group.GroupItemDecoration;
import com.anlia.library.group.SideGroupItemDecoration;
import com.anlia.recyclerviews.group.GroupActivity;
import com.anlia.recyclerviews.group.SideGroupActivity;
import com.anlia.recyclerviews.header.TopProjectionActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickEvent(View view) {
    	switch (view.getId()) {
            case R.id.btn_group_default:
                startActivity(new Intent(this, GroupActivity.class));
                break;
            case R.id.btn_group_side:
                startActivity(new Intent(this, SideGroupActivity.class));
                break;
            case R.id.btn_top_projection:
                startActivity(new Intent(this, TopProjectionActivity.class));
                break;
    	}
    }
}
