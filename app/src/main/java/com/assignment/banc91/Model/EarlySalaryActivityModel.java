package com.assignment.banc91.Model;

import android.content.Context;
import android.util.Log;

import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Contract.EarlySalaryActivityContract;
import com.assignment.banc91.Presenter.EarlySalaryActivityPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EarlySalaryActivityModel implements EarlySalaryActivityContract.Model {

    private static final String TAG="EarlyModel";
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

    @Override
    public double calculateEarlySalary(double salary){
        double earlySalary=0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date checkDate=null;
        Date today=null;
        try {
            checkDate = sdf.parse("15");
            today =sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(today!=null&& checkDate!=null) {
            if (today.after(checkDate)) {
                //after 15
                int diff = Integer.parseInt(sdf.format(today)) - Integer.parseInt(sdf.format(checkDate));
                Calendar c = Calendar.getInstance();
                int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                double oneDaySalary = salary/monthMaxDays;
                earlySalary = (15+diff)*oneDaySalary;
                Log.d(TAG, "calculateEarlySalary: early salary "+earlySalary);

            } else {
                // date is 15
                earlySalary = salary / 2;

                Log.d(TAG, "calculateEarlySalary: early salary half"+earlySalary);
            }
        }

        return earlySalary;
    }
}
