package com.ql.mynews.tab;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ql.mynews.R;
import com.ql.mynews.adapter.NewsOtherListAdapter;
import com.ql.mynews.http.HttpConst;
import com.ql.mynews.http.Https;
import com.ql.mynews.utils.NewsOtherBean;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
	private View v;

	private List<NewsOtherBean> list = new ArrayList<NewsOtherBean>();
	private NewsOtherListAdapter adapter;
	private ListView listview_;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment3, null);
		listview_ = (ListView) v.findViewById(R.id.listview_three);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Https.getRequestForNewsOther(getActivity(), "caijing", myHandler);
			}
		}).start();

		return v;
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

}
