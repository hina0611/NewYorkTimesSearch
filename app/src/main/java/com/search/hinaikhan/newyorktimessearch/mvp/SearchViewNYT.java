package com.search.hinaikhan.newyorktimessearch.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.adapters.ItemListAdapter;
import com.search.hinaikhan.newyorktimessearch.data.data.request.NYTRequest;
import com.search.hinaikhan.newyorktimessearch.data.data.response.Docs;
import com.search.hinaikhan.newyorktimessearch.data.data.response.NYTResponse;
import com.search.hinaikhan.newyorktimessearch.util.EndlessRecyclerViewScrollListener;

import java.util.Arrays;
import java.util.List;

import static com.search.hinaikhan.newyorktimessearch.common.Constant.beginDate;
import static com.search.hinaikhan.newyorktimessearch.common.Constant.newsDesk;
import static com.search.hinaikhan.newyorktimessearch.common.Constant.query;

/**
 * Created by hinaikhan on 9/19/17.
 */

public class SearchViewNYT extends Fragment {

    private static final String TAG = SearchViewNYT.class.getSimpleName();

    private View view;
    private RecyclerView rvRenderList;
    private Context mContect;
    private List<Docs> docsList;
    private SearchView mSearchView;
    ItemListAdapter mItemListAdapter;
    private NYTRequest mRequest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_type_view, container, false);

        onBindView(view);
//        setOnClickListener();

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mItemListAdapter = new ItemListAdapter(mContect, docsList);
        rvRenderList.setAdapter(mItemListAdapter);
        rvRenderList.setLayoutManager(gridLayoutManager);
        rvRenderList.addOnScrollListener(new EndlessRecyclerViewScrollListener((StaggeredGridLayoutManager) rvRenderList.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchNewsItems();

            }
        });

            return view;
    }

    private void onBindView(View view){
        rvRenderList = (RecyclerView) view.findViewById(R.id.rv_nytList);
//        mSearchView = (SearchView) view.findViewById(R.id.inputSearch);


    }

    private void fetchNewsItems(){
        SearchPresenter presenter = new SearchPresenter(this);
        mRequest = new NYTRequest();
        presenter.fetchNewsItems(mRequest);
    }

    public void renderNewsItems(NYTResponse response) {

        if(response != null){
            mItemListAdapter.setDocs(Arrays.asList(response.getResponse().getDocs()));
            mItemListAdapter.notifyDataSetChanged();
        }
    }

//        private void setOnClickListener(){
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }
}
