package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.R;

public class HomeActivity extends AppCompatActivity implements HomeActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initView() {

    }
}
