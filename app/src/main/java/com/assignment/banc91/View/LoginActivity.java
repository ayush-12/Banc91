package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.assignment.banc91.Contract.LoginActivityContract;
import com.assignment.banc91.Presenter.LoginActivityPresenter;
import com.assignment.banc91.R;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View {

    // View var
    private EditText mobileNumberEditText;
    private Button loginButton;

    private LoginActivityPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter=new LoginActivityPresenter(this,this);
    }

    @Override
    public void initView() {

        mobileNumberEditText = findViewById(R.id.mobile_number_edit_text);
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
