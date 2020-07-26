package com.keepshare.recyclerviewextension;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.keepshare.extension.listener.OnItemChildViewClickListener;
import com.keepshare.extension.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private SimpleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        initList();
        initData();
        initEvent();
    }

    private void initList() {
        final LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        mAdapter = new SimpleListAdapter(this);
        rv.setAdapter(mAdapter);
    }

    public void initData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("测试数据++" + i);
        }
        mAdapter.setDataList(dataList);
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onItemClick(int position, String itemData) {
                Toast.makeText(MainActivity.this, "itemView 位置:" + position + "---数据:" + itemData, Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemChildViewClickListener(new OnItemChildViewClickListener<String>() {
            @Override
            public void onItemClick(int viewId, int position, String itemData) {
                Toast.makeText(MainActivity.this, "child 位置:" + position + "+数据:" + itemData, Toast.LENGTH_SHORT).show();
            }
        });

    }
}