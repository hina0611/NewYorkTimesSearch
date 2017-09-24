package com.search.hinaikhan.newyorktimessearch.mvp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.data.data.request.NYTRequest;
import com.search.hinaikhan.newyorktimessearch.data.data.response.Docs;
import com.search.hinaikhan.newyorktimessearch.data.data.response.NYTResponse;

import java.util.List;

/**
 * Created by hinaikhan on 9/21/17.
 */

public class NTYResultActivity  extends AppCompatActivity {

    private static final String TAG = NTYResultActivity.class.getSimpleName();

    private TextView mTvNewsTitle;
    private Docs docsResponse;
    private TextView mTvNewsHeading;
    private TextView mTvNewsDescription;
    private ImageView mIvNewsProfilePic;

    private Context mContext;
    private List<Docs> docs;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content_view);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Object object = bundle.get("NEWS_RESPONSE");
            if(object != null && object instanceof Docs){
                docsResponse = (Docs) object;
            }
        }

        bindView();
        renderView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;

    }

    private void bindView(){
        mTvNewsTitle = (TextView) findViewById(R.id.item_title);
        mTvNewsDescription = (TextView) findViewById(R.id.tv_details_content);
        mIvNewsProfilePic = (ImageView) findViewById(R.id.item_image);
        mTvNewsHeading = (TextView) findViewById(R.id.tv_heading);
    }

    private void renderView(){
        if(docsResponse != null){
            mTvNewsHeading.setText(docsResponse.getDocument_type());
            mTvNewsDescription.setText(docsResponse.getSnippet());

            String imageURL = docsResponse.getPrimaryImageURL();
            if(imageURL != null){
                String absoluteImgUrl = "http://www.nytimes.com/" + imageURL;
                Glide.with(getApplicationContext()).load(Uri.parse(absoluteImgUrl)).into(mIvNewsProfilePic);

            }

        }
    }






}
