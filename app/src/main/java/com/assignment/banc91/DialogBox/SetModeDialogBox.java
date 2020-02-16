package com.assignment.banc91.DialogBox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.assignment.banc91.Contract.LoginActivityContract;

import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SetModeDialogBox {
    private Context mContext;
    private LoginActivityContract.View mView;

    private String[] availableModes = new String[]{"Light", "Dark"};

    private String selectedTheme;
    private String currentTheme;

    public SetModeDialogBox(Context context, LoginActivityContract.View view,String currentTheme){
        this.mContext=context;
        mView=view;
        this.currentTheme =currentTheme;
    }


    public void generateDialogBox() {

        List<String> tempList = Arrays.asList(availableModes);
        int selectedPosition = tempList.indexOf(currentTheme);
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Choose Theme");

        builder.setSingleChoiceItems(availableModes,selectedPosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedTheme= availableModes[which];
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               setThemeSharedPreference(selectedTheme);
               mView.restartOnThemeChanged();
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setThemeSharedPreference(String selectedTheme){
        SharedPreferences.Editor editor = mContext.getSharedPreferences
                ("selectedTheme", MODE_PRIVATE).edit();
        editor.putString("Theme", selectedTheme);
        editor.apply();
    }
}
