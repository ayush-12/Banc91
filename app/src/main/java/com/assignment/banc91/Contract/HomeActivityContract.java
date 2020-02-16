package com.assignment.banc91.Contract;

import com.assignment.banc91.Common.CardDetails;
import com.assignment.banc91.View.HomeActivity;

import java.util.List;
import java.util.Map;

public interface HomeActivityContract {
    interface View{
        void initView();
        void setViews(List<CardDetails> cardDetails);
        void setTxnListView(List<CardDetails> cardDetails);
        void hideEarlySalaryButton();
        void showEarlySalaryButton();

        HomeActivity returnActivity();
    }

    interface Model{
        void logoutUser();
        void getData();
        void setDataInLocal(List<CardDetails> cardDetailsList);

        boolean checkDateForEarlySalary();
    }

    interface Presenter{
        void onClick(android.view.View view);
        void callModelToLogoutUser();
        void callGetData();
        void passDataToViewToSet(List<CardDetails> cardDetails);
        void callModelToCheckDateForEarlySalary();

        HomeActivity getActivityFromView();

    }

}
