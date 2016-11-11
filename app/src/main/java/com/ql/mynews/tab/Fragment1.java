package com.ql.mynews.tab;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ql.mynews.R;
import com.ql.mynews.adapter.NewsListAdapter;
import com.ql.mynews.http.HttpConst;
import com.ql.mynews.http.Https;
import com.ql.mynews.utils.NewsAllBean;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    public static final String TAG = null;
    private View v;

    private List<NewsAllBean> list = new ArrayList<NewsAllBean>();
    private NewsListAdapter adapter;
    private ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment1, null);
        listview = (ListView) v.findViewById(R.id.listview);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Https.getRequestForNewsAll(getActivity(), "", myHandler);
            }
        }).start();

        return v;
    }

    private Handler myHandler = new Handler() {

        @SuppressWarnings("unchecked")
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case HttpConst.MSG_WHAT_NEWS:
                    if (msg.obj == null) {

                    } else {
                        list = (List<NewsAllBean>) msg.obj;
                        adapter = new NewsListAdapter(getActivity(), list);
                        listview.setAdapter(adapter);
                        Log.d(TAG,"数据加载完成");
                    }
                    break;

                default:
                    break;
            }
        }

        ;
    };


}