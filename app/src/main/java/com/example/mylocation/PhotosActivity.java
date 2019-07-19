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
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PhotosActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST=1;
    ArrayList<Image> arrayList_of_images = new ArrayList<Image>();

    protected void newIntent() {
        Intent intent = new Intent();
        intent.putExtra("photos", arrayList_of_images);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(PhotosActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(PhotosActivity.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                ActivityCompat.requestPermissions(PhotosActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(PhotosActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }

        else doStuff();
        newIntent();
    }

    public void getPhoto()
    {
        View listView_item = getLayoutInflater().inflate(R.layout.photolayout,null);
        ContentResolver contentResolver=getContentResolver();
        Uri photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor photoCursor=contentResolver.query(photoUri,null,null,null,null);

        if(photoCursor!=null && photoCursor.moveToFirst())
        {
            int photoTitle =photoCursor.getColumnIndex(MediaStore.Images.Media.TITLE);
            int photoDate =photoCursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int photoSize =photoCursor.getColumnIndex(MediaStore.Images.Media.SIZE);
            do {
                String currentTitle = photoCursor.getString(photoTitle);
                String currentDate = converToTime(photoCursor.getString(photoDate));
                String currentSize = converToSize(photoCursor.getString(photoSize));
                TextView textView_Name = (TextView) listView_item.findViewById(R.id.textView_Name_Photo);
                textView_Name.setText(currentTitle);
                TextView textView_Date= (TextView) listView_item.findViewById(R.id.textView_DateOfCreate);
                textView_Date.setText(currentDate);
                TextView textView_Size = (TextView) listView_item.findViewById(R.id.textView_Size);
                textView_Size.setText(currentSize);
                //arrayList_of_images.add(listView);
            } while (photoCursor.moveToNext());
        }
    }
    public static String converToTime(String timestamp)
    {
        long datetime = Long.parseLong(timestamp);
        Date date = new Date(datetime);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return formatter.format(date);
    }

    public static String converToSize(String size)
    {
        return Double.toString(Math.round(Double.parseDouble(size)/1000 * 100.0)/100.0)+" KB";
    }

    public void doStuff()
    {
        arrayList_of_images=new ArrayList<>();
        fillData();
    }

    public void fillData() {
        View listView_item = getLayoutInflater().inflate(R.layout.photolayout, null);
        ContentResolver contentResolver = getContentResolver();
        Uri photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor photoCursor = contentResolver.query(photoUri, null, null, null, null);
        if (photoCursor != null && photoCursor.moveToFirst()) {
            int photoTitle = photoCursor.getColumnIndex(MediaStore.Images.Media.TITLE);
            int photoDate = photoCursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
            int photoSize = photoCursor.getColumnIndex(MediaStore.Images.Media.SIZE);

            do {
                String currentTitle = photoCursor.getString(photoTitle);
                if (currentTitle.length()>27)
                {
                    currentTitle = currentTitle.substring(0,24)+"...";
                }
                String currentDate = converToTime(photoCursor.getString(photoDate));
                String currentSize = converToSize(photoCursor.getString(photoSize));
                arrayList_of_images.add(new Image(currentTitle, currentDate, currentSize));
            } while (photoCursor.moveToNext());
        }

    }

}

