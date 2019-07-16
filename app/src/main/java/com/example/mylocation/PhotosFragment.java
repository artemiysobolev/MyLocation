package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PhotosFragment extends Fragment {

    private ArrayList<Image> photosFromActivity;
    private ListView listView;
    private BoxAdapter boxAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_photos, container, false);

        photosFromActivity = new ArrayList<Image>();
        photosFromActivity = getArguments().getParcelableArrayList("photos");

        listView = (ListView) rootView.findViewById(R.id.photosListView);
        boxAdapter = new BoxAdapter(getContext(),photosFromActivity);
        listView.setAdapter(boxAdapter);

        return rootView;
    }
}