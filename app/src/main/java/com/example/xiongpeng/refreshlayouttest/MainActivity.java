package com.example.xiongpeng.refreshlayouttest;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    SwipeRefreshLayout swipeRefreshLayout = null;
    ListView list = null;
    List<String>  datas= datas = new ArrayList<>(Arrays.asList("AA", "BB", "CC", "DD", "EE"));
    public static int ADDDATA = 1;
    private ArrayAdapter<String> arrayAdapter;

    Handler handler = handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == ADDDATA){
                datas.addAll(Arrays.asList("SS", "WW"));
                arrayAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datas);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        list = (ListView)findViewById(R.id.list);

        swipeRefreshLayout.setOnRefreshListener(MainActivity.this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.CYAN);
        list.setAdapter(arrayAdapter);
    }

    @Override
    public void onRefresh() {
        Message msg = new Message();
        msg.what = ADDDATA;
        handler.sendMessage(msg);
    }
}
