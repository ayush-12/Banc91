package com.assignment.banc91.Contract;

import com.assignment.banc91.View.HomeActivity;

public interface HomeActivityContract {
    interface View{
        void initView();

        HomeActivity returnActivity();
    }

    interface Model{
        void logoutUser();
    }

    interface Presenter{
        void onClick(android.view.View view);
        void callModelToLogoutUser();

        HomeActivity getActivityFromView();
    }

}
