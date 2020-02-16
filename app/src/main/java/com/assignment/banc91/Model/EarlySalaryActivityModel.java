package com.assignment.banc91.Model;

import android.content.Context;

import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Contract.EarlySalaryActivityContract;
import com.assignment.banc91.Presenter.EarlySalaryActivityPresenter;

public class EarlySalaryActivityModel implements EarlySalaryActivityContract.Model {

    private Context mContext;
    private EarlySalaryActivityPresenter earlySalaryActivityPresenter;

    public EarlySalaryActivityModel(Context mContext,
                                    EarlySalaryActivityPresenter earlySalaryActivityPresenter) {

        this.mContext = mContext;
        this.earlySalaryActivityPresenter=earlySalaryActivityPresenter;
    }

    @Override
    public void logoutUser() {
        CommonClass commonClass = new CommonClass(mContext);
        commonClass.logoutUser(earlySalaryActivityPresenter.getActivityFromView());
    }
}
