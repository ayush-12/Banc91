package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.assignment.banc91.Contract.EarlySalaryActivityContract;
import com.assignment.banc91.R;

public class EarlySalaryActivity extends AppCompatActivity implements EarlySalaryActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_early_salary);
    }

    @Override
    public void initView() {

    }
}
