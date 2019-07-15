package com.example.mylocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MessagesFragment extends Fragment {

    private TextView messagesTextView;
    private String messagesFromActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);
        messagesTextView = (TextView) rootView.findViewById(R.id.messagesTextView);
        messagesFromActivity = getArguments().getString("messages");
        messagesTextView.setText(messagesFromActivity);

        return rootView;
    }
}