package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.Presenter.HomeActivityPresenter;
import com.assignment.banc91.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity implements HomeActivityContract.View {

    private HomeActivityPresenter mPresenter;

    private FloatingActionButton goToSalaryActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darkTheme);
        }
        else{
            setTheme(R.style.AppTheme);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPresenter=new HomeActivityPresenter(this,this);
    }

    @Override
    public void initView() {

        goToSalaryActivityButton = findViewById(R.id.salary_button);
        goToSalaryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClick(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout_menu_button: {
                break;
            }
            case R.id.select_theme_button: {
                break;
            }
        }
        return true;
    }
}
