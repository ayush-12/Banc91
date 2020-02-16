package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Contract.LoginActivityContract;
import com.assignment.banc91.DialogBox.SetModeDialogBox;
import com.assignment.banc91.Presenter.LoginActivityPresenter;
import com.assignment.banc91.R;
import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View {

    private static final String TAG="LoginActivity";
    public static String APP_SELECTED_THEME;

    // View var
    private EditText mobileNumberEditText;
    private CountryCodePicker countryCodePicker;
    private Button loginButton;

    private LoginActivityPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = getSharedPreferences("selectedTheme", MODE_PRIVATE);
        APP_SELECTED_THEME = prefs.getString("Theme", "Light");
        CommonClass commonClass = new CommonClass(this);
        commonClass.setTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter=new LoginActivityPresenter(this,this);
        if(mPresenter.callModelToGetIfUserLoggedIn()){
            mPresenter.goToHomeActivity();
        }
    }

    @Override
    public void initView() {

        mobileNumberEditText = findViewById(R.id.mobile_number_edit_text);
        countryCodePicker =findViewById(R.id.country_code_picker);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClick(view);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.logout_menu_button);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.select_theme_button: {
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                SetModeDialogBox setModeDialogBox = new SetModeDialogBox(this,this,
                        APP_SELECTED_THEME);
                setModeDialogBox.generateDialogBox();
                break;
            }
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void restartOnThemeChanged(){
        finish();
        startActivity(new Intent(this,LoginActivity.class));

    }

    @Override
    public void setErrorIfMobileNumberNotValid(String message) {
        mobileNumberEditText.setError(message);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public String getMobileNumber() {
        String mobileNumber = countryCodePicker.getSelectedCountryCodeWithPlus()
                + mobileNumberEditText.getText().toString();
        Log.d(TAG, "getMobileNumber: Mobile number "+mobileNumber);
        return mobileNumber;
    }
}
