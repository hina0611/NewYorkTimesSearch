package com.search.hinaikhan.newyorktimessearch.data.data.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class NYTResponse implements Parcelable{

    //Main class
    private String status;
    private String copyright;
    private Response response;

    public NYTResponse(){

    }

    public NYTResponse(String status, String copyright, Response response) {
        this.status = status;
        this.copyright = copyright;
        this.response = response;
    }

    protected NYTResponse(Parcel in) {
        status = in.readString();
        copyright = in.readString();
        response = in.readParcelable(Response.class.getClassLoader());
    }

    public static final Creator<NYTResponse> CREATOR = new Creator<NYTResponse>() {
        @Override
        public NYTResponse createFromParcel(Parcel in) {
            return new NYTResponse(in);
        }

        @Override
        public NYTResponse[] newArray(int size) {
            return new NYTResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeString(copyright);
        parcel.writeParcelable(response, i);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "NYTResponse{" +
                "status='" + status + '\'' +
                ", copyright='" + copyright + '\'' +
                ", response=" + response +
                '}';
    }
}
