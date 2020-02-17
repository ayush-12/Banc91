package com.assignment.banc91.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.banc91.Adapters.TxnAdapter;
import com.assignment.banc91.Adapters.ViewPagerAdapter;
import com.assignment.banc91.Common.CardDetails;
import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Common.TransactionClass;
import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.Presenter.HomeActivityPresenter;
import com.assignment.banc91.R;
import com.assignment.banc91.SqlLiteDb.CardDetailsSql;
import com.assignment.banc91.SqlLiteDb.CardDetailsSqlDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.assignment.banc91.View.LoginActivity.APP_SELECTED_THEME;

public class HomeActivity extends AppCompatActivity implements HomeActivityContract.View {

    private static final String TAG = "HomeActivity";

    private HomeActivityPresenter mPresenter;

    private FloatingActionButton goToSalaryActivityButton;
    private TextView transactionsTextView;

    private LinearLayout indicatorLayout;
    private View leftIndicatorView;
    private View rightIndicatorView;
    private View centerIndicatorView;

    private RecyclerView transactionsListView;
    private TxnAdapter txnAdapter;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private ProgressBar progressBar;

    private List<CardDetails> cardDetails;

    private int cardPosition;


    public static CardDetailsSqlDatabase cardDetailsSqlDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CommonClass commonClass = new CommonClass(this);
        commonClass.setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPresenter = new HomeActivityPresenter(this, this);

        cardDetailsSqlDatabase = Room.databaseBuilder(this, CardDetailsSqlDatabase.class,
                "CardDetailsSql").build();
        mPresenter.callModelToCheckDateForEarlySalary();
        showProgressBar();
        mPresenter.callGetData();
    }

    @Override
    public void initView() {
        progressBar = findViewById(R.id.progress_bar);
        indicatorLayout = findViewById(R.id.indicator_layout);
        leftIndicatorView = findViewById(R.id.left_indicator_view);
        rightIndicatorView = findViewById(R.id.right_indicator_view);
        centerIndicatorView = findViewById(R.id.center_indicator_view);
        //leftIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.selected_circle));

        goToSalaryActivityButton = findViewById(R.id.salary_button);
        goToSalaryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClick(view);
            }
        });
        transactionsTextView = findViewById(R.id.transactions_text_view);
        viewPager = findViewById(R.id.card_view_pager);
        transactionsListView = findViewById(R.id.transaction_list_view);
        transactionsListView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        transactionsListView.setLayoutManager(linearLayoutManager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "setTxnListView: setting list view " + viewPager.getCurrentItem());
                cardPosition = position;
                txnAdapter = new TxnAdapter(HomeActivity.this, cardDetails.get(viewPager.getCurrentItem()).getTransactions());
                transactionsListView.setAdapter(txnAdapter);
                if (position == 0) {
                    firstItem();
                } else if (position == cardDetails.size() - 1) {
                    lastItem();
                } else if (position > 0 && position < cardDetails.size() - 1) {
                    midItem();
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void setViews(List<CardDetails> cardDetails) {
        this.cardDetails = cardDetails;
        Log.d(TAG, "setViews: setting view " + cardDetails.size());
        viewPagerAdapter = new ViewPagerAdapter(this, cardDetails, this);
        viewPager.setAdapter(viewPagerAdapter);
        transactionsTextView.setVisibility(View.VISIBLE);
        indicatorLayout.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    @Override
    public void setTxnListView(List<CardDetails> cardDetails) {
        Log.d(TAG, "setTxnListView: setting list view " + viewPager.getCurrentItem());
        transactionsListView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        transactionsListView.setLayoutManager(linearLayoutManager);
        txnAdapter = new TxnAdapter(this, cardDetails.get(viewPager.getCurrentItem()).getTransactions());
        transactionsListView.setAdapter(txnAdapter);
    }

    @Override
    public void hideEarlySalaryButton() {
        Log.d(TAG, "hideEarlySalaryButton: Early salary Button hide");
        goToSalaryActivityButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEarlySalaryButton() {
        Log.d(TAG, "showEarlySalaryButton:  Early salary Button show");
        goToSalaryActivityButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void blockCard() {
        mPresenter.callModelToBlockCard(cardDetails.get(cardPosition).getCardNumber());

    }


    @Override
    public HomeActivity returnActivity() {
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


    public void lastItem() {
        leftIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.circle));
        centerIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.circle));
        rightIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.selected_circle));
    }

    public void firstItem() {
        leftIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.selected_circle));
        centerIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.circle));
        rightIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.circle));
    }

    public void midItem() {
        leftIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.circle));
        centerIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.selected_circle));
        rightIndicatorView.setBackground(ContextCompat.getDrawable(HomeActivity.this,R.drawable.circle));
    }
}
