package com.vladimirefimov.phototricks;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vladimirefimov.phototricks.viewPager.ShowPagerActivity;
import com.vladimirefimov.phototricks.viewPager.ViewPagerSampleActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vladimir Efimov on 18.08.2016.
 */
public class PhotoPresenter {
   private MainActivity context;

    public PhotoPresenter(MainActivity context) {
        this.context = context;
    }

    public void takePhoto() {
        Intent pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = getOutputMediaFileUri();
        context.currentImagesLocations.add(fileUri.toString());
        pictureActionIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        context.startActivityForResult(pictureActionIntent, Constants.CAMERA_REQUEST);
    }

    public void getFromGallery(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        context.startActivityForResult(i, Constants.GALLERY_REQUEST);
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


    public void showPhoto() {

        if (context.currentImagesLocations.size() <= context.images.size()) {
            for (int i = 0; i < context.currentImagesLocations.size(); i++) {
                ImageView tmp = context.images.get(i);
                Picasso.with(context)
                        .load(context.currentImagesLocations.get(i))
                        .resize(400, 400)
                        .into(tmp);
            }
        }

    }

    public void showLarge(){

        Intent intent = new Intent(context, ShowPagerActivity.class);
        context.startActivity(intent);


//        int i = context.imageContainer.indexOfChild(v);
//        Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(context, ViewPagerSampleActivity.class);
//        intent.putExtra("IMGS",context.currentImagesLocations.get(0));
//        context.startActivity(intent);

    }

}
