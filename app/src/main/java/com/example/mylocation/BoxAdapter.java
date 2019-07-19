package com.example.mylocation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater layoutInflater;
    private ArrayList<Image> objects;

    BoxAdapter(Context context,ArrayList<Image> images) {
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
        ((TextView) view.findViewById(R.id.textView_Name_Photo)).setText(image.getNameImage());
        ((TextView) view.findViewById(R.id.textView_DateOfCreate)).setText(image.getDateOfCreateImage());
        ((TextView) view.findViewById(R.id.textView_Size)).setText(image.getSizeImage());

        Uri uriFromPath = Uri.fromFile(new File(image.getRealPath()));

        Bitmap bitmap = null;
        InputStream inputStream;

        try {
            inputStream = ctx.getContentResolver().openInputStream(uriFromPath);
            bitmap = BitmapFactory.decodeStream(inputStream);
//            File file = new File(image.getRealPath());
//            if (file.exists()) {
//                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//            }
        }catch (FileNotFoundException e) {e.printStackTrace(); }

//        File file = new File(image.getRealPath());
//        if (file.exists()) {
//            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//        }
        Bitmap cropImg = Bitmap.createScaledBitmap(bitmap, 88 , 88,true);

        ((ImageView) view.findViewById(R.id.imageView)).setImageBitmap(cropImg);
        return view;
    }


    Image getImage(int i){
        return ((Image) getItem(i));
    }


}
