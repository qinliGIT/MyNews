package com.ql.mynews.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ql.mynews.R;
import com.ql.mynews.adapter.FragmentAdapter;
import com.ql.mynews.fragmentCustom.BounceScrollView;
import com.ql.mynews.fragmentCustom.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTABActivity extends FragmentActivity {
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("今日头条", "娱乐", "财经", "时尚", "体育",
            "国际", "国内", "军事", "社会","科技");

    private ViewPagerIndicator mIndicator;
    private BounceScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);
        initView();
        initDatas();
        mIndicator.setTabItemTitles(mDatas);
        mIndicator.setViewPager(mViewPager, mScrollView, 0);

    }

    /*
    ,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    private void initDatas() {
        // TODO Auto-generated method stub
        mTabContents.add(new Fragment1());
        mTabContents.add(new Fragment2());
        mTabContents.add(new Fragment3());
        mTabContents.add(new Fragment4());
        mTabContents.add(new Fragment5());
        mTabContents.add(new Fragment6());
        mTabContents.add(new Fragment7());
        mTabContents.add(new Fragment8());
        mTabContents.add(new Fragment9());
        mTabContents.add(new Fragment10());
        FragmentAdapter a = new FragmentAdapter(getSupportFragmentManager(), mTabContents);
        mViewPager.setAdapter(a);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initView() {
        mScrollView = (BounceScrollView) findViewById(R.id.id_scrollview);
        mViewPager = (ViewPager) findViewById(R.id.id_page_vp);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);

    }
}