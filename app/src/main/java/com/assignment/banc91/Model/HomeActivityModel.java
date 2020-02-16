package com.assignment.banc91.Model;

import android.content.Context;

import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.Presenter.HomeActivityPresenter;

public class HomeActivityModel implements HomeActivityContract.Model {
    private Context mContext;
    private HomeActivityPresenter homeActivityPresenter;

    public HomeActivityModel(Context mContext, HomeActivityPresenter homeActivityPresenter) {
        this.mContext=mContext;
        this.homeActivityPresenter=homeActivityPresenter;
    }

    @Override
    public void logoutUser() {
        CommonClass commonClass = new CommonClass(mContext);
        commonClass.logoutUser(homeActivityPresenter.getActivityFromView());
    }
}
