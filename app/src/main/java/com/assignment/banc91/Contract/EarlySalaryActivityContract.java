package com.assignment.banc91.Contract;

import com.assignment.banc91.View.EarlySalaryActivity;
import com.assignment.banc91.View.HomeActivity;

public interface EarlySalaryActivityContract {

    interface View{
        void initView();

        EarlySalaryActivity returnActivity();
    }

    interface Model{
        void logoutUser();

        double calculateEarlySalary(double salary);
    }

    interface Presenter{
        void onClick(android.view.View view);
        void callModelToLogoutUser();
        double getEarlySalaryFromModel(double salary);


        EarlySalaryActivity getActivityFromView();

    }


}
