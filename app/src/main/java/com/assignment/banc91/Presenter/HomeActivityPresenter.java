package com.assignment.banc91.Presenter;

import android.content.Context;

import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.Model.HomeActivityModel;

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
    }

}
