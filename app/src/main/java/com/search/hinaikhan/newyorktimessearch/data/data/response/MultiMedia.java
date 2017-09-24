package com.search.hinaikhan.newyorktimessearch.data.data.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class MultiMedia implements Parcelable{

    private String type;
    private String subtype;
    private String url;
    private int height;
    private int width;
    private int rank;
    private Legacy legacy;

    public MultiMedia(){

    }

    public MultiMedia(String type, String subtype, String url, int height, int width, int rank, Legacy legacy) {
        this.type = type;
        this.subtype = subtype;
        this.url = url;
        this.height = height;
        this.width = width;
        this.rank = rank;
        this.legacy = legacy;
    }

    protected MultiMedia(Parcel in) {
        type = in.readString();
        subtype = in.readString();
        url = in.readString();
        height = in.readInt();
        width = in.readInt();
        rank = in.readInt();
        legacy = in.readParcelable(Legacy.class.getClassLoader());
    }

    public static final Creator<MultiMedia> CREATOR = new Creator<MultiMedia>() {
        @Override
        public MultiMedia createFromParcel(Parcel in) {
            return new MultiMedia(in);
        }

        @Override
        public MultiMedia[] newArray(int size) {
            return new MultiMedia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(subtype);
        parcel.writeString(url);
        parcel.writeInt(height);
        parcel.writeInt(width);
        parcel.writeInt(rank);
        parcel.writeParcelable(legacy, i);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    @Override
    public String toString() {
        return "MultiMedia{" +
                "type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", url='" + url + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", rank=" + rank +
                ", legacy=" + legacy +
                '}';
    }
}
