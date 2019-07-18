package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MessagesFragment extends Fragment {

    private ArrayList<String> MessageListFromActivity;
    private ArrayAdapter<String> arAdapter;
    private ListView lstMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        MessageListFromActivity = new ArrayList<String>();
        MessageListFromActivity = getArguments().getStringArrayList( "messages");
        lstMessage = (ListView) rootView.findViewById(R.id.lstMessage);
        arAdapter = new ArrayAdapter<String>( rootView.getContext(), R.layout.fragment_message, R.id.MessageTextView, MessageListFromActivity);
        lstMessage.setAdapter(arAdapter);

        return rootView;
    }
}