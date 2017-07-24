package com.example.bibesh.click;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private File output = null;

    @Click(R.id.click)
    void clickImage() {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        output = new File(dir, "CameraContentDemo.jpeg");
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));


        if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(output), "image/jpeg");
                startActivity(i);
                finish();
            }
        }
    }
}


