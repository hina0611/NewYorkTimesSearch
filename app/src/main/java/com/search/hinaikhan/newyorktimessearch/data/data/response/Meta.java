package com.search.hinaikhan.newyorktimessearch.data.data.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class Meta implements Parcelable{

    private int hits;
    private int offset;
    private int time;

    public Meta(){

    }

    public Meta(int hits, int offset, int time) {
        this.hits = hits;
        this.offset = offset;
        this.time = time;
    }

    protected Meta(Parcel in) {
        hits = in.readInt();
        offset = in.readInt();
        time = in.readInt();
    }

    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(hits);
        parcel.writeInt(offset);
        parcel.writeInt(time);
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "hits=" + hits +
                ", offset=" + offset +
                ", time=" + time +
                '}';
    }
}
