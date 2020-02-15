package com.assignment.banc91.Presenter;

import android.content.Context;

import com.assignment.banc91.Contract.LoginActivityContract;
import com.assignment.banc91.Model.LoginActivityModel;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {

    private final String TAG = "LoginPresenter";
    private LoginActivityContract.View mView;
    private Context mContext;
    private LoginActivityContract.Model model;


    public LoginActivityPresenter(LoginActivityContract.View view, Context context){
        mView=view;
        mContext=context;
        initPresenter();
    }

    private void initPresenter(){
        model= new LoginActivityModel(mContext,this);
        mView.initView();
    }

    @Override
    public void onClick(android.view.View view) {
    }

}
