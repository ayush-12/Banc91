package com.assignment.banc91.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.assignment.banc91.Common.CardDetails;
import com.assignment.banc91.Contract.HomeActivityContract;
import com.assignment.banc91.R;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "viewPager";

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CardDetails> cardDetails;
    private HomeActivityContract.View mView;
    //private Integer [] arr= {R.drawable.white_logo,R.drawable.google_icon};

    public ViewPagerAdapter(Context context, List<CardDetails> cardDetails, HomeActivityContract.View mView) {
        this.mView = mView;
        this.cardDetails = cardDetails;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cardDetails.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.card_view_layout, null);
        Log.d(TAG, "instantiateItem: view pager pos " + position);
        Log.d(TAG, "instantiateItem: view pager pos  1 " + cardDetails.get(position));
        //mView.setTxnListView(cardDetails);
        CardView cardView = view.findViewById(R.id.card_view);
        final Button blockButton = view.findViewById(R.id.block_card_button);
        TextView cardNumberTextView = view.findViewById(R.id.card_number_text_view);
        TextView expiryTextView = view.findViewById(R.id.expiry_text_view);
        final TextView cvvTextView = view.findViewById(R.id.cvv_text_view);
        final TextView statusTextView = view.findViewById(R.id.status_text_view);
        final ImageView showCvv = view.findViewById(R.id.show_image_view);
        final ImageView hideCvv = view.findViewById(R.id.hide_image_view);

        showCvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideCvv.setVisibility(View.VISIBLE);
                showCvv.setVisibility(View.INVISIBLE);
                cvvTextView.setText(cardDetails.get(position).getCvv());
            }
        });
        hideCvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideCvv.setVisibility(View.INVISIBLE);
                showCvv.setVisibility(View.VISIBLE);
                cvvTextView.setText("***");
            }
        });
        cardNumberTextView.setText(cardDetails.get(position).getCardNumber());
        expiryTextView.setText(cardDetails.get(position).getExpiry());
        cvvTextView.setText("***");
        if (cardDetails.get(position).isActive()) {
            blockButton.setVisibility(View.VISIBLE);
            statusTextView.setText("Active");
            statusTextView.setTextColor(Color.parseColor("#228B22"));
        } else {
            blockButton.setVisibility(View.INVISIBLE);
            statusTextView.setText("Blocked");
            statusTextView.setTextColor(Color.RED);

        }

        blockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.blockCard();

                blockButton.setVisibility(View.INVISIBLE);
                statusTextView.setText("Blocked");
                statusTextView.setTextColor(Color.RED);

            }
        });
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }


}
