package com.example.mylocation;

import android.os.Parcel;
import android.os.Parcelable;

//made from usual class by http://www.parcelabler.com
public class Image implements Parcelable {
    private String nameImage,
                    dateOfCreateImage,
                    sizeImage,
                    realPath;


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

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath= realPath;
    }

    protected Image(String myName,String myDate,String mySize,String myRealPath) {
        nameImage=myName;
        dateOfCreateImage=myDate;
        sizeImage=mySize;
        realPath=myRealPath;
    }

    protected Image(Parcel in) {
        nameImage = in.readString();
        dateOfCreateImage = in.readString();
        sizeImage = in.readString();
        realPath = in.readString();
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
        dest.writeString(realPath);
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