package com.search.hinaikhan.newyorktimessearch.mvp;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.util.AppUtil;
import com.search.hinaikhan.newyorktimessearch.util.FilterDialog;

import static com.search.hinaikhan.newyorktimessearch.common.Constant.query;


public class SearchActivity extends AppCompatActivity {

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.showOverflowMenu();

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            bundle = new Bundle();
        }

        AppUtil.displayFragment(new SearchViewNYT(),getSupportFragmentManager(), bundle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
//                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mi_Filter:
                FilterDialog dialog = new FilterDialog();
                dialog.show(getFragmentManager(), "SHow Filter");
                return true;
            case R.id.action_search:
                onMenuItemSearchQuery();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void onMenuItemSearchQuery(){
        //TODO Implement me
    }


}





