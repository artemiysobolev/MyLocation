package com.example.mylocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

public class MessagesActivity extends AppCompatActivity {

    private StringBuilder builder;
    private String messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (MessagesActivity.this.hasSMSPermission()) {
            builder = new StringBuilder();
            MessagesActivity.this.todo_sms();
        } else {
            MessagesActivity.this.requestSMSPermission();
        }

        Intent intent = new Intent();
        intent.putExtra("messages", messages);
        setResult(RESULT_OK, intent);
        finish();

    }


    void todo_sms() {
        ContentResolver resolver = getContentResolver();
        Cursor cur = resolver.query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address", "body"}, null, null, null);
        if (cur != null && cur.moveToFirst()) {
            Integer i = cur.getCount();
            do {
                builder.append("\n");
                builder.append(i);
//                builder.append(cur.getString(0)); works incorrectly
                builder.append(". От кого:   ");
                builder.append(cur.getString(1));
                builder.append(". Сообщение:  ");
                builder.append(cur.getString(2));
                i--;
            } while (cur.moveToNext());
        }

        messages = builder.toString();
        if (cur != null) cur.close();


    }

    boolean hasSMSPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    final static int CODE_PERMISSION_SMS = 42;

    void requestSMSPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, CODE_PERMISSION_SMS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResult) {
        if (requestCode == CODE_PERMISSION_SMS) {
            if (grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                todo_sms();
            } else {
                Toast.makeText(this, "M?", Toast.LENGTH_SHORT).show();
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
    }

}