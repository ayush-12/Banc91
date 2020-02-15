package com.assignment.banc91.Presenter;

import android.content.Context;

import com.assignment.banc91.Contract.EarlySalaryActivityContract;
import com.assignment.banc91.Model.EarlySalaryActivityModel;

public class EarlySalaryActivityPresenter implements EarlySalaryActivityContract.Presenter {

    private final String TAG = "EarlySalaryPresenter";
    private EarlySalaryActivityContract.View mView;
    private Context mContext;
    private EarlySalaryActivityContract.Model model;


    public EarlySalaryActivityPresenter(EarlySalaryActivityContract.View view, Context context){
        mView=view;
        mContext=context;
        initPresenter();
    }

    private void initPresenter(){
        model= new EarlySalaryActivityModel(mContext,this);
        mView.initView();
    }

    @Override
    public void onClick(android.view.View view) {
    }
}
