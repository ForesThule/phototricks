package com.vladimirefimov.phototricks;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vladimirefimov.phototricks.viewPager.ViewPagerSampleActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends Activity {

    PhotoPresenter photoPresenter = new PhotoPresenter(this);


    Button takeFotoButton;

    Button galleryButton;

    LinearLayout imageContainer;

    ImageView iv_0;
    ImageView iv_1;
    ImageView iv_2;
    ImageView iv_3;
    ImageView iv_4;

    ArrayList<ImageView> images = new ArrayList<>();

  public ArrayList<String> currentImagesLocations = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageContainer = (LinearLayout) findViewById(R.id.ll_photo_container);

        iv_0 = (ImageView) findViewById(R.id.iv_0);
        iv_1 = (ImageView) findViewById(R.id.iv_1);
        iv_2 = (ImageView) findViewById(R.id.iv_2);
        iv_3 = (ImageView) findViewById(R.id.iv_3);
        iv_4 = (ImageView) findViewById(R.id.iv_4);

        images.add(iv_0);
        images.add(iv_1);
        images.add(iv_2);
        images.add(iv_3);
        images.add(iv_4);

        takeFotoButton = (Button) findViewById(R.id.take_photo);
        takeFotoButton = (Button) findViewById(R.id.gallery);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 777:
                    photoPresenter.showPhoto();
                    break;
                case 666:
                    currentImagesLocations.add(String.valueOf(data.getData()));
                    photoPresenter.showPhoto();
                    break;
            }
        }
    }

    public void showLarge(View v){
        photoPresenter.showLarge();
    }


    public void click(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                if (currentImagesLocations.size() < 5) {
                    photoPresenter.takePhoto();
                } else
                    Toast.makeText(this, "Out of photo limits", Toast.LENGTH_SHORT)
                            .show();
                break;
            case R.id.gallery:
                if (currentImagesLocations.size() < 5) {
                    photoPresenter.getFromGallery();
                } else
                    Toast.makeText(this, "Out of photo limits", Toast.LENGTH_SHORT)
                            .show();
                break;

        }
    }









}



