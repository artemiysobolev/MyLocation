package com.example.mylocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutDeviceActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_device);

        //getting info about device
        final String myDeviceBrand = "Device brand: " + android.os.Build.BRAND;
        final String myDeviceModel = "Device model: " + android.os.Build.MODEL;
        final String myDevice = "Device name: " + android.os.Build.DEVICE;
        final String myAndroidVersion = "Android version: " + android.os.Build.VERSION.RELEASE;
        final String myHardware = "Device Hardware name: " + android.os.Build.HARDWARE;
        final String myManufacturer = "Device manufacturer: " + android.os.Build.MANUFACTURER;

        final String Everything = myDeviceBrand +"\n"+ myDeviceModel +"\n"+ myDevice +"\n" + myAndroidVersion +"\n"+  myHardware +"\n"+ myManufacturer +"\n";

        textView = findViewById(R.id.textView);
        textView.setText(Everything);
    }
}
