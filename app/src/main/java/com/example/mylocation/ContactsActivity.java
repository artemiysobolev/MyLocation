package com.example.mylocation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    ListView listView ;
    ArrayList<String> StoreContacts ;
    ArrayAdapter<String> arrayAdapter ;
    Cursor cursor ;
    String name, phonenumber ;
    private static final int MY_PERMISSION_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StoreContacts = new ArrayList<String>();

        if(ContextCompat.checkSelfPermission(ContactsActivity.this,
                Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(ContactsActivity.this,
                    android.Manifest.permission.READ_CONTACTS))
            {
                ActivityCompat.requestPermissions(ContactsActivity.this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},MY_PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(ContactsActivity.this,
                        new String[]{android.Manifest.permission.READ_CONTACTS}, MY_PERMISSION_REQUEST);
            }
        } else {
            GetContactsIntoArrayList();
        }

        listView = (ListView) findViewById(R.id.contactsListView);

        Intent intent = new Intent();
            intent.putStringArrayListExtra("contacts", StoreContacts);
            setResult(RESULT_OK, intent);
            finish();
    }

    public void GetContactsIntoArrayList(){

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContacts.add(name + " "  + ":" + " " + phonenumber);
        }

        cursor.close();

    }

//    public void EnableRuntimePermission(){
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale(
//                com.example.mylocation.ContactsActivity.this,
//                Manifest.permission.READ_CONTACTS))
//        {
//
//            Toast.makeText(com.example.mylocation.ContactsActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
//
//        } else {
//
//            ActivityCompat.requestPermissions(com.example.mylocation.ContactsActivity.this,new String[]{
//                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
//
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
//
//
//        switch (RC) {
//
//            case RequestPermissionCode:
//
//                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    Toast.makeText(com.example.mylocation.ContactsActivity.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();
//
//                } else {
//
//                    Toast.makeText(com.example.mylocation.ContactsActivity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();
//
//                }
//                break;
//        }
//    }


}