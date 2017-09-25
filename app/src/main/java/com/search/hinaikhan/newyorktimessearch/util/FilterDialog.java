package com.search.hinaikhan.newyorktimessearch.util;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.mvp.SearchActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hinaikhan on 9/21/17.
 */

public class FilterDialog extends DialogFragment{

    private final String TAG = FilterDialog.class.getSimpleName();

    private RelativeLayout mRelativeLayoutArtContainer;
    private RelativeLayout mRelativeLayoutFashionAndStyleContainer;
    private RelativeLayout mRelativeLayoutSportsContainer;
    private RelativeLayout mRelativeLayoutDateContainer;

    private CheckBox mCheckBoxArt;
    private CheckBox mCheckBoxFashionAndStyle;
    private CheckBox mCheckBoxSports;

    private Button mBtnFilterApply;
    private Button mBtnFilterCancel;
    private TextView mTvDateFormat;

    private DatePickerDialog dialog;

    private SimpleDateFormat format;
    private SimpleDateFormat displayFormat;
    private Calendar calendar;
    private String date;

    private Spinner mSortOrder;
    private int order;
    private boolean checkArt;
    private boolean checkFAS;
    private boolean checkSports;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_helper_list_item, container);

        mRelativeLayoutArtContainer = (RelativeLayout) view.findViewById(R.id.relative_art_container);
        mRelativeLayoutFashionAndStyleContainer = (RelativeLayout) view.findViewById(R.id.relative_fashion_and_style_container);
        mRelativeLayoutSportsContainer = (RelativeLayout) view.findViewById(R.id.relative_sport_container);
        mRelativeLayoutDateContainer = (RelativeLayout) view.findViewById(R.id.relative_date_container);

        mCheckBoxArt = (CheckBox) view.findViewById(R.id.checkbox_art);
        mCheckBoxFashionAndStyle = (CheckBox) view.findViewById(R.id.checkbpx_fashion_and_style);
        mCheckBoxSports = (CheckBox) view.findViewById(R.id.checkbpx_sport);

        mBtnFilterApply = (Button) view.findViewById(R.id.filter_apply);
        mBtnFilterCancel = (Button) view.findViewById(R.id.filter_cancel);

        mSortOrder = (Spinner) view.findViewById(R.id.filter_sort_order) ;
        mTvDateFormat = (TextView) view.findViewById(R.id.tv_date_format);

        if(calendar == null){
            calendar = Calendar.getInstance();
        }
        format = new SimpleDateFormat("yyyyMMdd");
        displayFormat = new SimpleDateFormat("yyyy/MMM/dd");
        mSortOrder.setPrompt("Please select order");
        setOnClickListener();
        initView();
        return view;

    }

    private void initView(){
        SearchSettings searchSettings = ((SearchActivity)getActivity()).getSearchSettings();
        if (searchSettings.getBeginDate() != null) {
            date = displayFormat.format(searchSettings.getBeginDate());
        }
        if(date != null){
            mTvDateFormat.setText(date);
        }else {
            mTvDateFormat.setText(displayFormat.format(calendar.getTime()));
        }

        if (SearchSettings.SORT_OLDEST.equalsIgnoreCase(searchSettings.getSortOrder())) {
            mSortOrder.setSelection(1);
        } else {
            mSortOrder.setSelection(0);
        }

        checkArt = searchSettings.hasSelectedCategory(SearchSettings.CATEGORY_ARTS);
        checkFAS = searchSettings.hasSelectedCategory(SearchSettings.CATEGORY_FASHION_AND_STYLE);
        checkSports = searchSettings.hasSelectedCategory(SearchSettings.CATEGORY_SPORTS);

        mCheckBoxArt.setChecked(checkArt);
        mCheckBoxFashionAndStyle.setChecked(checkFAS);
        mCheckBoxSports.setChecked(checkSports);
    }


    private void setOnClickListener(){

        mBtnFilterApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyFilter();
            }
        });

        mBtnFilterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mRelativeLayoutDateContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        mTvDateFormat.setText(displayFormat.format(calendar.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }

        });

       mCheckBoxArt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
               checkArt = checked;
           }
       });

        mCheckBoxFashionAndStyle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                checkFAS = checked;
            }
        });

        mCheckBoxSports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                checkSports = checked;
            }
        });

    }

    @Override
    public void onResume() {

        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        //set layout as 75% of screen width
        window.setLayout ((int) (size.x * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }


    public void applyFilter(){

        String sortOrder = mSortOrder.getSelectedItem().toString();
        List<String> categories = new ArrayList<String>();
        if (checkArt) {
            categories.add(SearchSettings.CATEGORY_ARTS);
        }

        if (checkFAS) {
            categories.add(SearchSettings.CATEGORY_FASHION_AND_STYLE);
        }

        if (checkSports) {
            categories.add(SearchSettings.CATEGORY_SPORTS);
        }

        dismiss();

        ((SearchActivity)getActivity()).updateSearchSettings(calendar.getTime(), sortOrder, categories);

    }





}
