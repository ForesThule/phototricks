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

    private static final int CAMERA_REQUEST = 777;

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
                    showPhoto();
                    break;
            }
        }
    }


    public void click(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                if (currentImagesLocations.size() < 5) {
                    takePhoto();
                } else
                    Toast.makeText(this, "Out of photo limits", Toast.LENGTH_SHORT)
                            .show();
                break;

        }
    }

    public void showLarge(View v){

        int i = imageContainer.indexOfChild(v);
        Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ViewPagerSampleActivity.class);
        intent.putExtra("IMGS",currentImagesLocations.get(0));
        startActivity(intent);

    }

    public void takePhoto() {
        Intent pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = getOutputMediaFileUri();
        currentImagesLocations.add(fileUri.toString());
        pictureActionIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(pictureActionIntent, CAMERA_REQUEST);
    }

    private void showPhoto() {

        if (currentImagesLocations.size() <= images.size()) {
            for (int i = 0; i < currentImagesLocations.size(); i++) {
                ImageView tmp = images.get(i);
                Picasso.with(MainActivity.this)
                        .load(currentImagesLocations.get(i))
                        .resize(300, 300)
                        .into(tmp);
            }
        }

    }

    private static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "PHOTOTRICKS");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }


}



