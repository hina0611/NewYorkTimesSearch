package com.search.hinaikhan.newyorktimessearch.handlers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.data.data.request.NYTRequest;
import com.search.hinaikhan.newyorktimessearch.data.data.response.NYTResponse;
import com.search.hinaikhan.newyorktimessearch.mvp.SearchPresenter;
import com.search.hinaikhan.newyorktimessearch.network.NYTApiInterface;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;*/
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

import static android.R.attr.apiKey;
import static com.search.hinaikhan.newyorktimessearch.common.Constant.beginDate;
import static com.search.hinaikhan.newyorktimessearch.common.Constant.page;
import static com.search.hinaikhan.newyorktimessearch.common.Constant.sort;


/**
 * Created by hinaikhan on 9/20/17.
 */

public class NYTHandler {


    /**
     * Sample Url:
     *   https://api.nytimes.com/svc/search/v2/articlesearch.json?begin_date=20160112&sort=oldest&fq=news_desk:(%22Education%22%20%22Health%22)&api-key=4927c2f199d44a01a6775b67f9cc2948
     * @param presenter
     * @param //NYTRequest
     */
    //use okhttp
   /* public void fetchNewsItemsOld(final SearchPresenter presenter, final NYTRequest NYTRequest) {

        OkHttpClient client = new OkHttpClient();
        String baseUrl = presenter.getmViewSearch().getResources().getString(R.string.search_url);
        HttpUrl.Builder builder = HttpUrl.parse(baseUrl).newBuilder();
        if (NYTRequest.getBegin_date() != null) {
            builder.addQueryParameter("begin_date", NYTRequest.getBegin_date());
        }

        if (NYTRequest.getSort() != null) {
            builder.addQueryParameter("sort", NYTRequest.getSort());
        }

        if (NYTRequest.getFq() != null) {
            builder.addQueryParameter("fq", NYTRequest.getFq());
        }


        builder.addQueryParameter("page", new Integer(NYTRequest.getPage()).toString());
        builder.addQueryParameter("api-key", presenter.getmViewSearch().getResources().getString(R.string.api_key));

        String url = builder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("failure message", e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    // Read data on the worker thread
                    final String responseData = response.body().string();
                    Log.d("fetchNewsItemOnSuccess", responseData);

                    // Run view-related code back on the main thread
                    presenter.getmViewSearch().getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new GsonBuilder().create();
                            NYTResponse response = gson.fromJson(responseData, NYTResponse.class);
                            presenter.renderNewsItems(response);
                        }
                    });
                }
            }
        });
    }*/

    //New Implementation for Retrofit
    public void fetchNewsItems(final SearchPresenter presenter, final NYTRequest nytRequest) {

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(presenter.getmViewSearch().getResources().getString(R.string.search_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NYTApiInterface apiService = retrofit.create(NYTApiInterface.class);


        Call<NYTResponse> call = apiService.getNewsItems(presenter.getmViewSearch().getResources().getString(R.string.api_key),
                                                        nytRequest.getPage() + "", nytRequest.getBegin_date(), nytRequest.getSort(),
                                                        nytRequest.getFq());

        call.enqueue(new Callback<NYTResponse>() {
            @Override
            public void onResponse(Call<NYTResponse> call, Response<NYTResponse> response) {
                int statusCode = response.code();
                NYTResponse nytResponse = response.body();
                presenter.renderNewsItems(nytResponse);
            }

            @Override
            public void onFailure(Call<NYTResponse> call, Throwable t) {
                // Log error here since request failed
            }
        });

    }
}

