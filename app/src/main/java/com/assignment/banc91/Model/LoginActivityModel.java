package com.assignment.banc91.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Contract.LoginActivityContract;
import com.assignment.banc91.Presenter.LoginActivityPresenter;

import static android.content.Context.MODE_PRIVATE;

public class LoginActivityModel implements LoginActivityContract.Model {
    private Context mContext;
    private LoginActivityPresenter loginActivityPresenter;

    public LoginActivityModel(Context mContext, LoginActivityPresenter loginActivityPresenter) {
        this.mContext = mContext;
        this.loginActivityPresenter = loginActivityPresenter;
    }

    @Override
    public void setUserLoggedIn() {
        CommonClass commonClass = new CommonClass(mContext);
        commonClass.setIfUserLoggedIn(true);
    }

    @Override
    public boolean getIfUserLoggedIn() {
        SharedPreferences prefs = mContext.getSharedPreferences("userLoggedIn", MODE_PRIVATE);
        return prefs.getBoolean("LoggedIn", false);
    }

    @Override
    public boolean validateMobileNumber(String mobileNumber) {
        String validNumber ="^[+][0-9]{10,13}$";
        if (!mobileNumber.matches(validNumber))
            return false;
        else
            return true;
    }


}
