package com.search.hinaikhan.newyorktimessearch.data.data.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class Keywords implements Parcelable{

    private boolean isMajor;
    private int rank;
    private String name;
    private String value;

    public Keywords(){

    }

    public Keywords(boolean isMajor, int rank, String name, String value) {
        this.isMajor = isMajor;
        this.rank = rank;
        this.name = name;
        this.value = value;
    }

    protected Keywords(Parcel in) {
        isMajor = in.readByte() != 0;
        rank = in.readInt();
        name = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isMajor ? 1 : 0));
        dest.writeInt(rank);
        dest.writeString(name);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Keywords> CREATOR = new Creator<Keywords>() {
        @Override
        public Keywords createFromParcel(Parcel in) {
            return new Keywords(in);
        }

        @Override
        public Keywords[] newArray(int size) {
            return new Keywords[size];
        }
    };

    public boolean isMajor() {
        return isMajor;
    }

    public void setMajor(boolean major) {
        isMajor = major;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Creator<Keywords> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Keywords{" +
                "isMajor=" + isMajor +
                ", rank=" + rank +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
