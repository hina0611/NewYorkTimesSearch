package com.search.hinaikhan.newyorktimessearch.data.data.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class Legacy implements Parcelable{

    private String wide;
    private String wideheight;
    private String widewidth;

    public Legacy(){

    }

    public Legacy(String wide, String wideheight, String widewidth) {
        this.wide = wide;
        this.wideheight = wideheight;
        this.widewidth = widewidth;
    }

    protected Legacy(Parcel in) {
        wide = in.readString();
        wideheight = in.readString();
        widewidth = in.readString();
    }

    public static final Creator<Legacy> CREATOR = new Creator<Legacy>() {
        @Override
        public Legacy createFromParcel(Parcel in) {
            return new Legacy(in);
        }

        @Override
        public Legacy[] newArray(int size) {
            return new Legacy[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(wide);
        parcel.writeString(wideheight);
        parcel.writeString(widewidth);
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    public String getWideheight() {
        return wideheight;
    }

    public void setWideheight(String wideheight) {
        this.wideheight = wideheight;
    }

    public String getWidewidth() {
        return widewidth;
    }

    public void setWidewidth(String widewidth) {
        this.widewidth = widewidth;
    }

    @Override
    public String toString() {
        return "Legacy{" +
                "wide='" + wide + '\'' +
                ", wideheight='" + wideheight + '\'' +
                ", widewidth='" + widewidth + '\'' +
                '}';
    }
}
