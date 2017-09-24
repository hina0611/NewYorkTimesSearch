package com.search.hinaikhan.newyorktimessearch.data.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class Response implements Parcelable{

    private Docs[] docs;
    private Meta meta;

    public Response(){

    }

    public Response(Docs[] docs, Meta meta) {
        this.docs = docs;
        this.meta = meta;
    }

    protected Response(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public Docs[] getDocs() {
        return docs;
    }

    public void setDocs(Docs[] docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Response{" +
                "docs=" + Arrays.toString(docs) +
                ", meta=" + meta +
                '}';
    }
}

