package com.search.hinaikhan.newyorktimessearch.mvp;

import com.search.hinaikhan.newyorktimessearch.data.data.request.NYTRequest;
import com.search.hinaikhan.newyorktimessearch.data.data.response.Docs;
import com.search.hinaikhan.newyorktimessearch.data.data.response.NYTResponse;
import com.search.hinaikhan.newyorktimessearch.handlers.NYTHandler;

/**
 * Created by hinaikhan on 9/19/17.
 */

public class SearchPresenter {

    private SearchViewNYT mSearchView;
    private NTYResultActivity mSearchACtivityResult;

    public SearchPresenter(SearchViewNYT mSearchView){
       this.mSearchView = mSearchView;
    }

    public SearchPresenter(NTYResultActivity mSearchACtivityResult) {
        this.mSearchACtivityResult = mSearchACtivityResult;
    }


    public SearchViewNYT getmViewSearch(){
        return mSearchView;
    }

    public void fetchNewsItems(NYTRequest request) {
        NYTHandler handler = new NYTHandler();
        handler.fetchNewsItems(this, request);
    }

    public void renderNewsItems(NYTResponse response){
        if(response != null){
            mSearchView.renderNewsItems(response);
        }
    }

//    public void renderNewsResult(Docs docs){
//        NYTHandler handler = new NYTHandler();
//        handler.fetchNewsItems(docs);
//    }

    
}
