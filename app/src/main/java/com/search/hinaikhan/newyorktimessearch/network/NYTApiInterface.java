package com.search.hinaikhan.newyorktimessearch.network;

import com.search.hinaikhan.newyorktimessearch.data.data.response.NYTResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by hinaikhan on 9/25/17.
 */

public interface NYTApiInterface {

    @GET("articlesearch.json")
    Call<NYTResponse> getNewsItems(@Query("api-key") String apiKey, @Query("page") String page,
                                   @Query("begin_date") String beginDate, @Query("sort") String sort,
                                   @Query("fq") String fq);
}


