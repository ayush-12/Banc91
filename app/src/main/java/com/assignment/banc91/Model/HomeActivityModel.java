package com.assignment.banc91.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.assignment.banc91.Common.CardDetails;
import com.assignment.banc91.Common.CommonClass;
import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.FirebaseClasses.FirebaseDB;
import com.assignment.banc91.Presenter.HomeActivityPresenter;
import com.assignment.banc91.SqlLiteDb.CardDetailsSql;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.assignment.banc91.View.HomeActivity.cardDetailsSqlDatabase;

public class HomeActivityModel implements HomeActivityContract.Model {
    private static final String TAG = "HomeModel";

    private Context mContext;
    private HomeActivityPresenter homeActivityPresenter;

    private List<CardDetails> mainCardDetails;
    public HomeActivityModel(Context mContext, HomeActivityPresenter homeActivityPresenter) {
        this.mContext = mContext;
        this.homeActivityPresenter = homeActivityPresenter;
    }

    @Override
    public void logoutUser() {
        CommonClass commonClass = new CommonClass(mContext);
        commonClass.logoutUser(homeActivityPresenter.getActivityFromView());
    }

    @Override
    public void getData() {

        final FirebaseDB firebaseDB = new FirebaseDB(this);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: checkinggg size "+cardDetailsSqlDatabase.cardDetailsSqlDAO().getAllData().size());
                if(cardDetailsSqlDatabase.cardDetailsSqlDAO().getAllData().size()<21){
                    // call firebase
                    Log.d(TAG, "run: checkinggg not exist");
                    firebaseDB.getData();

                }
                else{
                    mainCardDetails = new ArrayList<>();
                    Log.d(TAG, "run: checkinggg exist ");
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            List<String> uniqueCard = cardDetailsSqlDatabase.cardDetailsSqlDAO().fetchDistinctCard();
                            Log.d(TAG, "run: unique card "+uniqueCard.size());

                            for(int i = 0 ; i<uniqueCard.size();i++){
                                List<CardDetailsSql> cardDetailsSqls =cardDetailsSqlDatabase.cardDetailsSqlDAO().getData(uniqueCard.get(i));
                                formattingData(cardDetailsSqls);
                            }
                            homeActivityPresenter.passDataToViewToSet(mainCardDetails);
                        }
                    });

                }
            }
        });
    }

    @Override
    public void setDataInLocal(final List<CardDetails> cardDetailsList) {
        for(int i =0 ;i<cardDetailsList.size();i++) {
            for (int j = 0; j < cardDetailsList.get(i).getTransactions().size(); j++) {
                final CardDetails cardDetails = cardDetailsList.get(i);
                final CardDetailsSql cardDetailsSql = new CardDetailsSql();
                cardDetailsSql.setCardNumber(cardDetails.getCardNumber());
                cardDetailsSql.setCvv(cardDetails.getCvv());
                cardDetailsSql.setExpiry(cardDetails.getExpiry());
                cardDetailsSql.setActive(cardDetails.isActive());
                cardDetailsSql.setTransactionId(cardDetails.getTransactions().get(j).get("transactionId").toString());
                cardDetailsSql.setTransactionDate(cardDetails.getTransactions().get(j).get("date").toString());
                cardDetailsSql.setCredited((boolean)cardDetails.getTransactions().get(j).get("isCredited"));
                cardDetailsSql.setAmount(cardDetails.getTransactions().get(j).get("amount").toString());
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: checkinggg ");
                        cardDetailsSqlDatabase.cardDetailsSqlDAO().addNoteToDB(cardDetailsSql);

                    }
                });
            }
        }
        homeActivityPresenter.passDataToViewToSet(cardDetailsList);
    }

    @Override
    public void blockCard(final String cardNumber) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                cardDetailsSqlDatabase.cardDetailsSqlDAO().blockCard(cardNumber,false);
            }
        });
    }

    @Override
    public boolean checkDateForEarlySalary() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date checkDate=null;
        Date today=null;
        try {
            checkDate = sdf.parse("14");
            today =sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(today!=null&& checkDate!=null) {
            if (today.after(checkDate)){
                return true;
            }
        }
        return false;
    }

    private void formattingData(List<CardDetailsSql> cardDetailsSql){
        CardDetails cardDetails =  new CardDetails();
        List<Map<String,Object>> list = new ArrayList<>();
        cardDetails.setCardNumber(cardDetailsSql.get(0).getCardNumber());
        cardDetails.setExpiry(cardDetailsSql.get(0).getExpiry());
        cardDetails.setCvv(cardDetailsSql.get(0).getCvv());
        cardDetails.setActive(cardDetailsSql.get(0).isActive());
        for(int i = 0 ; i<cardDetailsSql.size();i++){
            Map<String,Object> map = new HashMap<>();
            map.put("transactionId",cardDetailsSql.get(i).getTransactionId());
            map.put("amount",cardDetailsSql.get(i).getAmount());
            map.put("isCredited",cardDetailsSql.get(i).isCredited());
            map.put("date",cardDetailsSql.get(i).getTransactionDate());
            list.add(map);
        }
        cardDetails.setTransactions(list);

        mainCardDetails.add(cardDetails);
    }

}
