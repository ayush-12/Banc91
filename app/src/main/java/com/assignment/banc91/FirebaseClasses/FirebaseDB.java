package com.assignment.banc91.FirebaseClasses;

import android.util.Log;

import androidx.annotation.NonNull;

import com.assignment.banc91.Common.CardDetails;
import com.assignment.banc91.Model.HomeActivityModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDB {
    private static final String TAG = "Firebase";

    private HomeActivityModel homeActivityModel;

    public FirebaseDB(HomeActivityModel homeActivityModel){
        this.homeActivityModel = homeActivityModel;
    }

    public void getData(){
        final List<CardDetails> cardDetailsList = new ArrayList<>();
        DatabaseReference databaseReference = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("Card Details");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CardDetails cardDetails = postSnapshot.getValue(CardDetails.class);
                    cardDetailsList.add(cardDetails);
                    Log.d(TAG, "onDataChange: " + cardDetailsList.size());
                }

                Log.d(TAG, "onDataChange: data " + cardDetailsList.size());
                Log.d(TAG, "onDataChange: data +" + cardDetailsList.get(0).getTransactions());
                homeActivityModel.setDataInLocal(cardDetailsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
