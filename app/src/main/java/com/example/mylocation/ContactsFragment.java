package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    private ArrayList<String> contactListFromActivity;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_contacts, container, false);
        contactListFromActivity = new ArrayList<String>();
        contactListFromActivity = getArguments().getStringArrayList("contacts");
        listView = (ListView) rootView.findViewById(R.id.listview1);
        arrayAdapter = new ArrayAdapter<String>(rootView.getContext(),R.layout.fragment_contacts, R.id.contactsTextView, contactListFromActivity);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}