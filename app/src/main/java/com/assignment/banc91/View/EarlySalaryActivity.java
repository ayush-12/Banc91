package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.assignment.banc91.Contract.EarlySalaryActivityContract;
import com.assignment.banc91.Presenter.EarlySalaryActivityPresenter;
import com.assignment.banc91.R;

public class EarlySalaryActivity extends AppCompatActivity implements EarlySalaryActivityContract.View {

    private EarlySalaryActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_early_salary);

        mPresenter=new EarlySalaryActivityPresenter(this,this);
    }

    @Override
    public void initView() {

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
