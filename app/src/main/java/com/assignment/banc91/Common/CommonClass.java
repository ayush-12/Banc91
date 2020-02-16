package com.assignment.banc91.Common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.assignment.banc91.R;
import com.assignment.banc91.View.LoginActivity;

import static android.content.Context.MODE_PRIVATE;
import static com.assignment.banc91.View.LoginActivity.APP_SELECTED_THEME;

public class CommonClass {

    private Context context;

    public CommonClass(Context context){
        this.context=context;
    }

    public void setTheme(){
        if(APP_SELECTED_THEME.equalsIgnoreCase("Light")){
            context.setTheme(R.style.AppTheme);
        }
        else{
            context.setTheme(R.style.darkTheme);
        }
    }

    public void setIfUserLoggedIn(boolean isUserLoggedIn){
        SharedPreferences.Editor editor = context.getSharedPreferences
                ("userLoggedIn", MODE_PRIVATE).edit();
        editor.putBoolean("LoggedIn", isUserLoggedIn);
        editor.apply();
    }

    public void logoutUser(Activity activity){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        setIfUserLoggedIn(false);
        activity.finish();
    }
}
