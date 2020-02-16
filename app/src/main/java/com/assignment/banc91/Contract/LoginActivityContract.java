package com.assignment.banc91.Contract;

public interface LoginActivityContract {
    interface View{
        void initView();
        void restartOnThemeChanged();
        void setErrorIfMobileNumberNotValid(String message);
        void finishActivity();

        String getMobileNumber();

    }

    interface Model{
        void setUserLoggedIn();
        boolean getIfUserLoggedIn();

        boolean validateMobileNumber(String mobileNumber);
    }

    interface Presenter{
        void onClick(android.view.View view);
        void goToHomeActivity();
        boolean callModelToGetIfUserLoggedIn();

    }

}
