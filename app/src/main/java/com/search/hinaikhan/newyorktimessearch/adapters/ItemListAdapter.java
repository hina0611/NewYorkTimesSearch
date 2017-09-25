package com.search.hinaikhan.newyorktimessearch.adapters;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.data.data.response.Docs;
import com.search.hinaikhan.newyorktimessearch.data.data.response.MultiMedia;
import java.util.List;

/**
 * Created by hinaikhan on 9/19/17.
 */

public class ItemListAdapter extends RecyclerView.Adapter {

    private final static String TAG = ItemListAdapter.class.getSimpleName();

    private Context mContext;
    private List<Docs> docs;

    private final int NORMAL = 0;
    private final int TEXTONLY = 1;


    public ItemListAdapter(Context mContext, List<Docs> docs) {
        this.mContext = mContext;
        this.docs = docs;

    }

    //involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case NORMAL: {
                View view = inflater.inflate(R.layout.item_content_view, parent, false);
                viewHolder = new ItemListViewHolder(view);
                break;
            }

            case TEXTONLY: {
                View view = inflater.inflate(R.layout.text_only_item, parent, false);
                viewHolder = new TextOnlyViewHolder(view);
                break;
            }
        }

        return viewHolder;
    }



    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        final Docs document = docs.get(position);
        switch (holder.getItemViewType()) {
            case NORMAL: {
                ItemListViewHolder viewHolder = (ItemListViewHolder) holder;
                viewHolder.mTvNewstitle.setText(document.getHeadline().getMain());
                viewHolder.mBtnApplyTags.setText(document.getNew_desk());
                Glide.with(holder.itemView.getContext()).load(mContext.getString(R.string.nytimes_url) + document.getMultimedia()[0].getUrl()).into(viewHolder.mImgvNewsImage);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        PendingIntent pendingIntent = getPendingIntent(document.getWeb_url());
                        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.share_icon);
                        builder.setActionButton(bitmap, "Share", pendingIntent);
                        CustomTabsIntent intentCustomTab = builder.build();
                        intentCustomTab.launchUrl(mContext, Uri.parse(document.getWeb_url()));
                        builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                    }
                });
                break;


            }

            case TEXTONLY:{

                TextOnlyViewHolder viewHolder = (TextOnlyViewHolder) holder;
                viewHolder.mTvOnlyTitle.setText(document.getSnippet());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        PendingIntent pendingIntent = getPendingIntent(document.getWeb_url());
                        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.share_icon);
                        builder.setActionButton(bitmap, "Share", pendingIntent);
                        CustomTabsIntent intentCustomTab = builder.build();
                        intentCustomTab.launchUrl(mContext, Uri.parse(document.getWeb_url()));
                        builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                    }
                });
                break;
            }


        }


//        mTvNewsContent.setText(document.getSnippet());
//
//        String imgUrl = document.getPrimaryImageURL();
//        if (imgUrl != null) {
//            String absoluteImgUrl = "http://www.nytimes.com/" + imgUrl;
//            Glide.with(mContext).load(Uri.parse(absoluteImgUrl)).into(mImgvNewsImage);
//        }
//

//
//
//        mCardViewItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(mContext, NTYResultActivity.class);
//                intent.putExtra("NEWS_RESPONSE" , document);
//                mContext.startActivity(intent);
//
//        }
//        });

    }

    @Override
    public int getItemViewType(int position) {
        MultiMedia[] medias = new MultiMedia[0];
        final Docs document = docs.get(position);
         medias = document.getMultimedia();
        if(medias == null || medias.length == 0) {
            return TEXTONLY;
        }
        return NORMAL;
    }



    @Override
    public int getItemCount() {
        return docs.size() ;
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder {
        protected ImageView mImgvNewsImage;
        protected TextView mTvNewstitle;
        protected Button mBtnApplyTags;

        public ItemListViewHolder(View itemView) {
            super(itemView);

            mImgvNewsImage = (ImageView) itemView.findViewById(R.id.img_news);
            mTvNewstitle = (TextView) itemView.findViewById(R.id.tv_news_content);
            mBtnApplyTags = (Button) itemView.findViewById(R.id.btn_tag);
        }
    }

    public class TextOnlyViewHolder extends RecyclerView.ViewHolder{
        protected TextView mTvOnlyTitle;
        public TextOnlyViewHolder(View itemView) {
            super(itemView);

            mTvOnlyTitle = (TextView) itemView.findViewById(R.id.item_title);
        }
    }

    private PendingIntent getPendingIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        return PendingIntent.getActivity(mContext, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}