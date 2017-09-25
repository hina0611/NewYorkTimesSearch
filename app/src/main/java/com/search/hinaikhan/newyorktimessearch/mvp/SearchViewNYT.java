package com.search.hinaikhan.newyorktimessearch.mvp;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.adapters.ItemListAdapter;
import com.search.hinaikhan.newyorktimessearch.data.data.request.NYTRequest;
import com.search.hinaikhan.newyorktimessearch.data.data.response.Docs;
import com.search.hinaikhan.newyorktimessearch.data.data.response.NYTResponse;
import com.search.hinaikhan.newyorktimessearch.util.AppUtil;
import com.search.hinaikhan.newyorktimessearch.util.EndlessRecyclerViewScrollListener;
import com.search.hinaikhan.newyorktimessearch.util.SearchSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.search.hinaikhan.newyorktimessearch.common.Constant.page;


/**
 * Created by hinaikhan on 9/19/17.
 */

public class SearchViewNYT extends Fragment {

    private static final String TAG = SearchViewNYT.class.getSimpleName();

    private View view;
    private RecyclerView rvRenderList;
    private List<Docs> docsList;
    ItemListAdapter mItemListAdapter;
    private NYTRequest mRequest;
    private EndlessRecyclerViewScrollListener scrollListener;
    private ProgressBar pbLoading;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_type_view, container, false);

        onBindView(view);
//        setOnClickListener();

        StaggeredGridLayoutManager LayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        docsList = new ArrayList<Docs>();
        mItemListAdapter = new ItemListAdapter(getContext(), docsList);
        rvRenderList.setAdapter(mItemListAdapter);
        rvRenderList.setLayoutManager(LayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(LayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "OnLoadMore Fetching newsitems for page number - " +page);
                fetchNewsItems(page);
            }

        };

        // Adds the scroll listener to RecyclerView
        rvRenderList.addOnScrollListener(scrollListener);

        //First load
        scrollListener.resetState();
        scrollListener.onLoadMore(0, docsList.size(), rvRenderList);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(!AppUtil.isNetworkAvailable(getContext()) || !AppUtil.isOnline()) {
            Toast.makeText(getContext(), "No Internet available", Toast.LENGTH_SHORT).show();
        }
    }




    private void onBindView(View view){
        rvRenderList = (RecyclerView) view.findViewById(R.id.rv_nytList);
        pbLoading = (ProgressBar) view.findViewById(R.id.pbLoading);
    }

    public void refreshView() {
        docsList.clear();
        mItemListAdapter.notifyDataSetChanged();
        scrollListener.resetState();
        scrollListener.onLoadMore(1, docsList.size(), rvRenderList);
    }

    private void fetchNewsItems(int page){
        pbLoading.setVisibility(View.VISIBLE);
        SearchSettings searchSettings = ((SearchActivity)getActivity()).getSearchSettings();
        SearchPresenter presenter = new SearchPresenter(this);
        mRequest = new NYTRequest();
        mRequest.setPage(page);

        String fq = searchSettings.buildFqQuery();
        if (fq != null && !fq.isEmpty()) {
            mRequest.setFq(fq);
        }

        if (SearchSettings.SORT_OLDEST.equalsIgnoreCase(searchSettings.getSortOrder())) {
            mRequest.setSort("oldest");
        }

        if (searchSettings.getBeginDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            mRequest.setBegin_date(sdf.format(searchSettings.getBeginDate()));
        }

        if(searchSettings.getQuery() != null && !searchSettings.getQuery().isEmpty()){
            mRequest.setQ(searchSettings.getQuery());

        }

        presenter.fetchNewsItems(mRequest);
    }

    public void renderNewsItems(NYTResponse response) {
        pbLoading.setVisibility(View.GONE);

        if(response != null){
            int curSize = mItemListAdapter.getItemCount();

            docsList.addAll(Arrays.asList(response.getResponse().getDocs()));

            //mItemListAdapter.notifyItemRangeInserted(curSize, docsList.size() - 1);
            mItemListAdapter.notifyDataSetChanged();
        }
    }


}
