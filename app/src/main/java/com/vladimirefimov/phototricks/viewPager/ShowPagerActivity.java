package com.vladimirefimov.phototricks.viewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.vladimirefimov.phototricks.R;

/**
 * Created by Vladimir Efimov on 18.08.2016.
 */
public class ShowPagerActivity extends AppCompatActivity{
    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);
        mCustomPagerAdapter = new CustomPagerAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

}
