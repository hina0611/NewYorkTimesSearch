package com.search.hinaikhan.newyorktimessearch.mvp;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.data.data.response.Docs;


/**
 * Created by hinaikhan on 9/21/17.
 */

public class NTYResultActivity  extends AppCompatActivity {

    private static final String TAG = NTYResultActivity.class.getSimpleName();

    private Docs docsResponse;
    private boolean launchedUrl= false;

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

//        myWebView = (WebView) findViewById(R.id.webview);
//        myWebView.getSettings().setLoadsImagesAutomatically(true);
//        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        myWebView.setWebViewClient(new WebViewURL());
//
//        String url = docsResponse.getWeb_url();
//
//        if(url != null) {
//            Uri path = Uri.parse("http://www.nytimes.com" + url);
//            myWebView.loadUrl(url);
//
//        }
//        myWebView.getSettings().setUseWideViewPort(true);
//        // Zoom out if the content width is greater than the width of the viewport
//        myWebView.getSettings().setLoadWithOverviewMode(true);

    }


    private PendingIntent getPendingIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        return PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);

    }


    // Manages the behavior when URLs are loaded
    private class WebViewURL extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        if (launchedUrl) {
            finish();
        } else {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

            PendingIntent pendingIntent = getPendingIntent(docsResponse.getWeb_url());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_icon);
            builder.setActionButton(bitmap, "Share", pendingIntent);
            CustomTabsIntent intentCustomTab = builder.build();
            intentCustomTab.launchUrl(NTYResultActivity.this, Uri.parse(docsResponse.getWeb_url()));
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorAccent));
            launchedUrl = true;
        }
    }


}
