package com.assignment.banc91.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.banc91.R;

import java.util.List;
import java.util.Map;

public class TxnAdapter extends RecyclerView.Adapter<TxnAdapter.TxnViewHolder> {

    private static final String TAG="TxnAdapter";
    private Context mContext;
    private List<Map<String,Object>> txnDetails;


    public TxnAdapter(Context context, List<Map<String,Object>> txnDetails)
    {
        mContext=context;
        this.txnDetails=txnDetails;
    }



    @NonNull
    @Override
    public TxnAdapter.TxnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.txn_list_view,parent,false);
        return new TxnViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TxnAdapter.TxnViewHolder holder, int position) {
        Map<String,Object> txn = txnDetails.get(position);
        holder.transactionDateTextView.setText(txn.get("date").toString());
        holder.transactionIdTextView.setText(txn.get("transactionId").toString());
        if((boolean)txn.get("isCredited")) {
            holder.amountTextView.setText("+"+txn.get("amount").toString());
            holder.amountTextView.setTextColor(Color.GREEN);
        }
        else{
            holder.amountTextView.setText("-"+txn.get("amount").toString());
            holder.amountTextView.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return txnDetails.size();
    }

    public class TxnViewHolder extends RecyclerView.ViewHolder{

        TextView transactionIdTextView;
        TextView transactionDateTextView;
        TextView amountTextView;


        public TxnViewHolder(View view){
            super(view);
            transactionIdTextView = view.findViewById(R.id.transaction_id_text_view);
            transactionDateTextView = view.findViewById(R.id.transaction_date_text_view);
            amountTextView=view.findViewById(R.id.amount_text_view);


        }
    }
}
