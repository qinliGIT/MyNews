package com.ql.mynews.tab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ql.mynews.R;
import com.ql.mynews.adapter.NewsOtherListAdapter;
import com.ql.mynews.http.HttpConst;
import com.ql.mynews.http.Https;
import com.ql.mynews.utils.NewsAllBean;
import com.ql.mynews.utils.NewsOtherBean;
import com.ql.mynews.web.WebViewActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Fragment5 extends Fragment implements AdapterView.OnItemClickListener {
    private WeakReference<View> v;

    private List<NewsOtherBean> list = new ArrayList<NewsOtherBean>();
    private NewsOtherListAdapter adapter;
    private ListView listview_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null || v.get() == null) {
            View view=inflater.inflate(R.layout.fragment5, null);
            v = new WeakReference<View>(view);
        } else {
            ViewGroup parent = (ViewGroup) v.get().getParent();
            if (parent != null) {
                parent.removeView(v.get());
            }
        }
//        v = inflater.inflate(R.layout.fragment5, null);
        listview_ = (ListView) v.get().findViewById(R.id.listview_five);
        listview_.setOnItemClickListener(this);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Https.getRequestForNewsOther(getActivity(), "tiyu", myHandler);
            }
        }).start();

        return v.get();
    }

    private Handler myHandler = new Handler() {
        @SuppressWarnings("unchecked")
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case HttpConst.MSG_WHAT_NEWS_TIYU:
                    if (msg.obj == null) {

                    } else {
                        list = (List<NewsOtherBean>) msg.obj;
                        adapter = new NewsOtherListAdapter(getActivity(), list);
                        listview_.setAdapter(adapter);
                    }
                    break;

                default:
                    break;
            }
        }

        ;
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsOtherBean news = (NewsOtherBean) parent.getItemAtPosition(position);
        Intent in = new Intent(getActivity(), WebViewActivity.class);
        in.putExtra("url",news.getUrl());
        startActivity(in);
    }
}
