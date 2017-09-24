package com.search.hinaikhan.newyorktimessearch.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.data.data.response.Docs;
import com.search.hinaikhan.newyorktimessearch.data.data.response.MultiMedia;
import com.search.hinaikhan.newyorktimessearch.mvp.NTYResultActivity;

import java.util.List;


/**
 * Created by hinaikhan on 9/19/17.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListAdapterViewHolder> {

    private final static String TAG = ItemListAdapter.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mInflater;
    private View view;
    private List<Docs> docs;
    private List<MultiMedia> multiMedias;
    private CardView mCardViewItem;

    public ImageView mImgvNewsImage;
    public TextView mTvNewsContent;

    public ItemListAdapter(Context mContext, List<Docs> docs) {
        this.mContext = mContext;
        this.docs = docs;

    }

    public void setDocs(List<Docs> docs) {
        this.docs = docs;
    }

    //involves inflating a layout from XML and returning the holder
    @Override
    public ItemListAdapter.ItemListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_content_view, parent, false);

        ItemListAdapter.ItemListAdapterViewHolder viewHolder = new ItemListAdapter.ItemListAdapterViewHolder(view);

        return viewHolder;
    }

    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ItemListAdapter.ItemListAdapterViewHolder holder, int position) {

        final Docs document = docs.get(position);
        mTvNewsContent.setText(document.getSnippet());

        String imgUrl = document.getPrimaryImageURL();
        if (imgUrl != null) {
            String absoluteImgUrl = "http://www.nytimes.com/" + imgUrl;
            Glide.with(mContext).load(Uri.parse(absoluteImgUrl)).into(mImgvNewsImage);
        }

        mCardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NTYResultActivity.class);
                intent.putExtra("NEWS_RESPONSE" , document);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return docs != null ? docs.size() :0 ;
    }

    public class ItemListAdapterViewHolder extends RecyclerView.ViewHolder {


        public ItemListAdapterViewHolder(View itemView) {
            super(itemView);

            mContext = view.getContext();

            mImgvNewsImage = (ImageView) itemView.findViewById(R.id.img_news);
            mTvNewsContent = (TextView) itemView.findViewById(R.id.tv_news_content);
            mCardViewItem = (CardView) itemView.findViewById(R.id.cardview_item);


        }
    }
}