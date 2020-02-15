package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.assignment.banc91.Contract.LoginActivityContract;
import com.assignment.banc91.R;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {

    }
}
