package com.vladimirefimov.phototricks.viewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vladimirefimov.phototricks.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladimir.efimov on 16.08.2016.
 */
public class ViewPagerSampleActivity extends Activity {


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String imgs = "file:///storage/emulated/0/Pictures/PHOTOTRICKS/IMG_20160816_163149.jpg";
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+imgs+"<<<<<<<<<<<<<<<<<<<<<<<<<<");

        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();


        View page = inflater.inflate(R.layout.page, null);
//        ImageView imageView = (ImageView)findViewById(R.id.imageViewPager);

        pages.add(page);

        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

        setContentView(viewPager);




    }
}