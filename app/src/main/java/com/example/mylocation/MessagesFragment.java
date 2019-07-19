package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class MessagesFragment extends Fragment {

    private ArrayList<String> messageListFromActivity;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_messages, container, false);
        messageListFromActivity = new ArrayList<String>();
        messageListFromActivity = getArguments().getStringArrayList("messages");
        listView = (ListView) rootView.findViewById(R.id.messagesListView);
        arrayAdapter = new ArrayAdapter<String>(rootView.getContext(),R.layout.fragment_messages, R.id.messagesTextView, messageListFromActivity);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}