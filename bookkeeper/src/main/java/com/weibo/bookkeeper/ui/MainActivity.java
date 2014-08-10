package com.weibo.bookkeeper.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.weibo.bookkeeper.R;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class MainActivity extends Activity {

    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }

    private void init() {
    }
}