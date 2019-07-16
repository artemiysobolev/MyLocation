package com.example.mylocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Image> objects;

    BoxAdapter(Context context,ArrayList<Image> images)
    {
        ctx=context;
        objects=images;
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.photolayout, parent, false);
        }

        Image image=getImage(i);

        ((TextView) view.findViewById(R.id.textView_Name_Photo)).setText(image.nameImage);
        ((TextView) view.findViewById(R.id.textView_DateOfCreate)).setText(image.dateOfCreateImage);
        ((TextView) view.findViewById(R.id.textView_Size)).setText(image.sizeImage);

        return view;
    }


    Image getImage(int i)
    {
        return ((Image) getItem(i));
    }


}
