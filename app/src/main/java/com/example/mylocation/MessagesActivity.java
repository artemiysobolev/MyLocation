package com.example.mylocation;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;



public class MessagesActivity extends AppCompatActivity {
    private ListView lstMessage;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ArrayList<String> MessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstMessage = (ListView) findViewById(R.id.lstMessage);
        int permissionCheck = ContextCompat.checkSelfPermission( this,
                Manifest.permission.READ_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            showContacts();
        }else{
            ActivityCompat.requestPermissions( this,
                    new String[]{Manifest.permission.READ_SMS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        }

        Intent intent = new Intent();
        intent.putStringArrayListExtra("messages", MessageList);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            showContacts();
        } else {
            Toast.makeText(this,
                    "Until you grant the permission, we cannot display the name",
                    Toast.LENGTH_SHORT).show();
        }
    }



    private void showContacts() {
        Uri indoxUri = Uri.parse("content://sms/inbox");
        MessageList =  new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(indoxUri, null, null, null, null);
        while (cursor.moveToNext()){
            String number = cursor.getString(cursor.getColumnIndexOrThrow("address")).toString();
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body")).toString();
            MessageList.add("Number:    " + number + "\n" + "Body:  " + body);
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                MessageList);
        lstMessage.setAdapter(adapter);

    }


}