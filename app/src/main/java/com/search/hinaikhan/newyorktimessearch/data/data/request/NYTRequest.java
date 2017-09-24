package com.search.hinaikhan.newyorktimessearch.data.data.request;

/**
 * Created by hinaikhan on 9/20/17.
 */

public class NYTRequest{


//    https://api.nytimes.com/svc/search/v2/articlesearch.json?begin_date=20160112&sort=oldest&fq=news_desk:(%22Education%22%20%22Health%22)&api-key=4927c2f199d44a01a6775b67f9cc2948

    //get list of parameter https://developer.nytimes.com/article_search_v2.json#/Console/GET/articlesearch.json
    private String q;
    private String fq;
    private String begin_date;
    private String end_date;
    private String sort;
    private int page;

    public NYTRequest(){

    }

    public NYTRequest(String q, String fq, String begin_date, String end_date, String sort, int page) {
        this.q = q;
        this.fq = fq;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.sort = sort;
        this.page = page;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
