package com.search.hinaikhan.newyorktimessearch.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.search.hinaikhan.newyorktimessearch.R;

/**
 * Created by hinaikhan on 9/19/17.
 */

public class AppUtil {


    public static void displayFragment(Fragment mFragment, FragmentManager mFragmentManager, Bundle mBundle){

        if(mFragment == null || mFragmentManager == null || mBundle == null){
            return;
        }
        mFragment.setArguments(mBundle);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, mFragment);

        fragmentTransaction.commit();


    }


}
