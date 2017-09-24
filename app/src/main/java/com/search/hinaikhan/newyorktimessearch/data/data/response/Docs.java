package com.search.hinaikhan.newyorktimessearch.data.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.HashMap;

import static android.R.attr.type;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class Docs implements Parcelable{

    private String web_url;
    private String snippet;
    private HashMap blog;
    private String source;
    private MultiMedia[] multimedia;
    private Headline headline;
    private Keywords[] keywords;
    private String pub_date;
    private String document_type;
    private String new_desk;
    private String section_name;
    private HashMap byline;
    private String type_of_material;
    @SerializedName("_id")
    private String id;
    private int score;

    public Docs(){

    }

    public Docs(String web_url, String snippet, HashMap blog, String source, MultiMedia[] multimedia,
                Headline headline, Keywords[] keywords, String pub_date, String document_type,
                String new_desk, String section_name, HashMap byline, String type_of_material, String id, int score) {
        this.web_url = web_url;
        this.snippet = snippet;
        this.blog = blog;
        this.source = source;
        this.multimedia = multimedia;
        this.headline = headline;
        this.keywords = keywords;
        this.pub_date = pub_date;
        this.document_type = document_type;
        this.new_desk = new_desk;
        this.section_name = section_name;
        this.byline = byline;
        this.type_of_material = type_of_material;
        this.id = id;
        this.score = score;
    }

    protected Docs(Parcel in) {
        web_url = in.readString();
        snippet = in.readString();
        blog = in.readHashMap(HashMap.class.getClassLoader());
        source = in.readString();
        multimedia = in.createTypedArray(MultiMedia.CREATOR);
        headline = in.readParcelable(Headline.class.getClassLoader());
        keywords = in.createTypedArray(Keywords.CREATOR);
        pub_date = in.readString();
        document_type = in.readString();
        new_desk = in.readString();
        section_name = in.readString();
        byline = in.readHashMap(HashMap.class.getClassLoader());
        type_of_material = in.readString();
        id = in.readString();
        score = in.readInt();
    }

    public String getPrimaryImageURL(){
        String url = null;
        MultiMedia[] multiMedias = this.getMultimedia();
        if(multiMedias != null && multiMedias.length > 0){
            for (MultiMedia multiMedia : multiMedias) {
                String mediaType = multiMedia.getType();
                if (mediaType != null && mediaType.equalsIgnoreCase("image")) {
                    url = multiMedia.getUrl();
                    break;
                }
            }
        }
        return url;
    }
    public static final Creator<Docs> CREATOR = new Creator<Docs>() {
        @Override
        public Docs createFromParcel(Parcel in) {
            return new Docs(in);
        }

        @Override
        public Docs[] newArray(int size) {
            return new Docs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(web_url);
        parcel.writeString(snippet);
        parcel.writeMap(blog);
        parcel.writeString(source);
        parcel.writeTypedArray(multimedia, flags);
        parcel.writeParcelable(headline, flags);
        parcel.writeTypedArray(keywords, flags);
        parcel.writeString(pub_date);
        parcel.writeString(document_type);
        parcel.writeString(new_desk);
        parcel.writeString(section_name);
        parcel.writeMap(byline);
        parcel.writeString(type_of_material);
        parcel.writeString(id);
        parcel.writeInt(score);
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MultiMedia[] getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(MultiMedia[] multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public Keywords[] getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords[] keywords) {
        this.keywords = keywords;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }



    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }


    public String getType_of_material() {
        return type_of_material;
    }

    public void setType_of_material(String type_of_material) {
        this.type_of_material = type_of_material;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HashMap getBlog() {
        return blog;
    }

    public void setBlog(HashMap blog) {
        this.blog = blog;
    }

    public String getNew_desk() {
        return new_desk;
    }

    public void setNew_desk(String new_desk) {
        this.new_desk = new_desk;
    }

    public HashMap getByline() {
        return byline;
    }

    public void setByline(HashMap byline) {
        this.byline = byline;
    }

    @Override
    public String toString() {
        return "Docs{" +
                "web_url='" + web_url + '\'' +
                ", snippet='" + snippet + '\'' +
                ", blog=" + blog +
                ", source='" + source + '\'' +
                ", multimedia=" + Arrays.toString(multimedia) +
                ", headline=" + headline +
                ", keywords=" + Arrays.toString(keywords) +
                ", pub_date='" + pub_date + '\'' +
                ", document_type='" + document_type + '\'' +
                ", new_desk='" + new_desk + '\'' +
                ", section_name='" + section_name + '\'' +
                ", byline=" + byline +
                ", type_of_material='" + type_of_material + '\'' +
                ", id='" + id + '\'' +
                ", score=" + score +
                '}';
    }
}
