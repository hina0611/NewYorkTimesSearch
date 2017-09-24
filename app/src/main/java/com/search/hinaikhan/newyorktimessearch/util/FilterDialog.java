package com.search.hinaikhan.newyorktimessearch.util;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.search.hinaikhan.newyorktimessearch.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.R.attr.x;
import static com.search.hinaikhan.newyorktimessearch.common.Constant.beginDate;

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
        if(date != null){
            mTvDateFormat.setText(date);
        }else {
            mTvDateFormat.setText(displayFormat.format(calendar.getTime()));
        }
        mSortOrder.setSelection(order);
        mCheckBoxArt.setChecked(checkArt);
        mCheckBoxFashionAndStyle.setChecked(checkFAS);
        mCheckBoxSports.setChecked(checkSports);
    }


    private void setOnClickListener(){

        mBtnFilterApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
        String temp = (mCheckBoxArt.isChecked() ? getString(R.string.art) + " " : " " )
                + (mCheckBoxFashionAndStyle.isChecked() ? getString(R.string.fashion_and_style) + " "  : " " )
                + (mCheckBoxSports.isChecked() ? getString(R.string.sports) + " " : "")  ;

        date = mTvDateFormat.getText().toString();

    }



}
