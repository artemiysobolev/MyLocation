package com.example.mylocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DeviceInfoActivity extends AppCompatActivity {

    private String info;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        //getting info about device
        final String myDeviceBrand = "Device brand: " + android.os.Build.BRAND;
        final String myDeviceModel = "Device model: " + android.os.Build.MODEL;
        final String myDevice = "Device name: " + android.os.Build.DEVICE;
        final String myAndroidVersion = "Android version: " + android.os.Build.VERSION.RELEASE;
        final String myHardware = "Device Hardware name: " + android.os.Build.HARDWARE;
        final String myManufacturer = "Device manufacturer: " + android.os.Build.MANUFACTURER;

        info = myDeviceBrand + "\n" + myDeviceModel + "\n" + myDevice + "\n" + myAndroidVersion + "\n" + myHardware + "\n" + myManufacturer + "\n";
        intent = new Intent();
        intent.putExtra("info",info);
        setResult(RESULT_OK, intent);
        finish();
    }
}