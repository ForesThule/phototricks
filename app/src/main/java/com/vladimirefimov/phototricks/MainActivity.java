package com.vladimirefimov.phototricks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.support.v4.graphics.BitmapCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends Activity implements View.OnClickListener {

    Button takeFotoButton;

    Button galleryButton;

    ImageView iv;

    ArrayList<Uri> currentImagesLocations = new ArrayList<>();

    int counter = currentImagesLocations.size();

//    MyListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv_0);
        takeFotoButton = (Button) findViewById(R.id.take_photo);
    }

    public void takePhoto() {
        if (counter < 5) {
            createPhoto();
//            dispatchTakePictureIntent();
        } else Toast.makeText(this, "Фотопленка закончилась", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public void onClick(View v) {
        // do whatever you want here based on the view being passed
        switch (v.getId()) {
            case R.id.take_photo:
                takePhoto();
                break;
            //handle multiple view click events

        }
    }

    private void showPhoto() {

        Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(currentImagesLocations.get(0)));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>THAT FOTO PATH"+currentImagesLocations.get(0));

//        Picasso.with(MainActivity.this)
//                .load(currentImagesLocations.get(0))
//                .into(iv);

        iv.setImageBitmap(bitmap);


    }






    /**
     * create File for photo plasement
     * we get filePath here
     *
     */
    private void createPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = null;
        try {
            file = createImageFile();
            System.out.println(">>>>>>>>>>>>>>>>>>>"+"FileCrtd"+file.getAbsolutePath());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file.getAbsolutePath());
            startActivityForResult(intent, 777);
            currentImagesLocations.add(Uri.parse(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp =
                new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
        String imageFileName = "PHOTOTRX_" + timeStamp;
        File image = File.createTempFile(
                imageFileName,
                ".JPG",
                getAlbumDir()
        );

        return image;
    }

    private File getAlbumDir() {
        File dir = null;
        String APP_PATH_SD_CARD = "/Phototricks";
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_PATH_SD_CARD;
        try {
            dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            Log.w("creating file error", e.toString());
        }
        return dir;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                System.out.println("File not created");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 777);
            }
        }
    }

}



