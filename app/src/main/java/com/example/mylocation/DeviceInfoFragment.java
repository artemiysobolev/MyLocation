package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DeviceInfoFragment extends Fragment {

    private TextView deviceInfoTextView;
    private String deviceInfoFromActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_device_info, container, false);
        deviceInfoTextView = (TextView) rootView.findViewById(R.id.infoTextView);
        deviceInfoFromActivity = getArguments().getString("info");
        deviceInfoTextView.setText(deviceInfoFromActivity);

        return rootView;
    }
}