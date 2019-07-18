package com.example.mylocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PhotosActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST=1;
    ArrayList<Image> listView = new ArrayList<Image>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(PhotosActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(PhotosActivity.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(PhotosActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(PhotosActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }

        else doStuff();

        Intent intent = new Intent();
        intent.putExtra("photos", listView);
        setResult(RESULT_OK, intent);
        finish();
    }


    public static String converToTime(String timestamp) {
        long datetime = Long.parseLong(timestamp);
        Date date = new Date(datetime);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return formatter.format(date);
    }

    public static String converToSize(String size) {
        Double newSize = Math.round(Double.parseDouble(size)/1000 * 100.0)/100.0;
        if (newSize>=1000){
            newSize=Math.round(newSize/1000 * 100.0)/100.0;
            return Double.toString(newSize)+" MB";
        }
        return Double.toString(newSize)+" KB";
    }

    public void doStuff() {
        listView=new ArrayList<>();
        fillData();
    }

    public void fillData() {
        ContentResolver contentResolver = getContentResolver();
        Uri photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = contentResolver.query(photoUri, null, null, null, null);


        if (cursor  != null && cursor.moveToFirst()) {
            int title = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME);
            int date = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int size = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);
            int realPath = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            while (cursor.moveToNext()) {
                String currentTitle = cursor.getString(title);
                if (currentTitle.length()>27) {
                    currentTitle = currentTitle.substring(0,24)+"...";
                }
                String currentRealPath = cursor.getString(realPath);
                String currentDate = converToTime(cursor.getString(date));
                String currentSize = converToSize(cursor.getString(size));
                listView.add(new Image(currentTitle, currentDate, currentSize,currentRealPath));
            }
        }
        cursor .close();

    }

}
