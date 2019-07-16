package com.example.mylocation;

import android.os.Parcel;
import android.os.Parcelable;

//made from usual class by http://www.parcelabler.com
public class Image implements Parcelable {
    public String getDateOfCreateImage() {
        return dateOfCreateImage;
    }

    public void setDateOfCreateImage(String dateOfCreateImage) {
        this.dateOfCreateImage = dateOfCreateImage;
    }

    public String getSizeImage() {
        return sizeImage;
    }

    public void setSizeImage(String sizeImage) {
        this.sizeImage = sizeImage;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    private String nameImage;
    private String dateOfCreateImage;
    private String sizeImage;

    Image(String my_name,String my_date,String my_size) {
        nameImage=my_name;
        dateOfCreateImage=my_date;
        sizeImage=my_size;
    }

    protected Image(Parcel in) {
        nameImage = in.readString();
        dateOfCreateImage = in.readString();
        sizeImage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameImage);
        dest.writeString(dateOfCreateImage);
        dest.writeString(sizeImage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}