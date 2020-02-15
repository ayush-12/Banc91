package com.assignment.banc91.Presenter;

import android.content.Context;
import android.content.Intent;

import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.Model.HomeActivityModel;
import com.assignment.banc91.R;
import com.assignment.banc91.View.EarlySalaryActivity;

public class HomeActivityPresenter implements HomeActivityContract.Presenter {
    private final String TAG = "HomePresenter";
    private HomeActivityContract.View mView;
    private Context mContext;
    private HomeActivityContract.Model model;


    public HomeActivityPresenter(HomeActivityContract.View view, Context context){
        mView=view;
        mContext=context;
        initPresenter();
    }

    private void initPresenter(){
        model= new HomeActivityModel(mContext,this);
        mView.initView();
    }

    @Override
    public void onClick(android.view.View view) {
        if(view.getId()== R.id.salary_button){
            Intent intent = new Intent(mContext, EarlySalaryActivity.class);
            mContext.startActivity(intent);
        }
    }

}
