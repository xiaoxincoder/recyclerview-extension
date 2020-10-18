package com.keepshare.recyclerviewextension;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.keepshare.extension.CommonAdapter;
import com.keepshare.extension.CommonViewHolder;
import com.keepshare.extension.listener.OnItemChildViewClickListener;
import com.keepshare.extension.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private SimpleListAdapter mAdapter;

    private View emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        emptyView = findViewById(R.id.empty_layout);
        initList();
        initData();
        initEvent();
    }

    private void initList() {
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        mAdapter = new SimpleListAdapter(this);
        mAdapter.setEmptyView(emptyView, 1);
        rv.setAdapter(mAdapter);
    }

    public void initData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            dataList.add("测试数据++" + i);
        }
        mAdapter.setStartCheck(true);
        mAdapter.setDataList(dataList);
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(CommonViewHolder<String> viewHolder, View itemView, CommonAdapter<String> adapter, int position, String itemData) {
                Toast.makeText(MainActivity.this, "itemView 位置:" + position + "---数据:" + itemData, Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemChildViewClickListener(new OnItemChildViewClickListener<String>() {
            @Override
            public void onItemClick(CommonViewHolder<String> viewHolder, CommonAdapter<String> adapter, View childView, int position, String itemData) {
                switch (childView.getId()) {
                    case R.id.item_text_one:
                        Toast.makeText(MainActivity.this, "child 左侧 位置:" + position + "+数据:" + itemData, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_text_two:
                        Toast.makeText(MainActivity.this, "child 右侧 位置:" + position + "+数据:" + itemData, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }
}