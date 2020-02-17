package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Contract.EarlySalaryActivityContract;
import com.assignment.banc91.Presenter.EarlySalaryActivityPresenter;
import com.assignment.banc91.R;

public class EarlySalaryActivity extends AppCompatActivity implements EarlySalaryActivityContract.View {

    private EarlySalaryActivityPresenter mPresenter;

    private Button availSalaryButton;
    private TextView earlySalaryAmountTextView;

    private double salary =30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CommonClass commonClass = new CommonClass(this);
        commonClass.setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_early_salary);

        mPresenter=new EarlySalaryActivityPresenter(this,this);
        double earlySalary = Math.round(mPresenter.getEarlySalaryFromModel(salary) * 100.0) / 100.0;
        if(earlySalary>0){
            earlySalaryAmountTextView.setText(String.valueOf(earlySalary));
        }
    }

    @Override
    public void initView() {
        earlySalaryAmountTextView = findViewById(R.id.early_salary_text_view);
        availSalaryButton =findViewById(R.id.avail_salary_button);
        availSalaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EarlySalaryActivity.this, "Salary will be credited soon",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public EarlySalaryActivity returnActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.select_theme_button);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout_menu_button: {
                mPresenter.callModelToLogoutUser();
                break;
            }
        }
        return true;
    }
}
