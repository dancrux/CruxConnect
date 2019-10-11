package com.cruxrepublic.cruxconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.Window;

import com.cruxrepublic.cruxconnect.Utils.PreferenceKeys;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Mainactivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFirstLogin();
        init();
    }
    private void init (){
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, homeFragment, getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();

    }
    private void isFirstLogin() {
        Log.d(TAG, "isFirstLogin: checked if this is first login");
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin = preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true);

        if (isFirstLogin){
            Log.d(TAG, "isFirstLogin: Launched Alert Dialog.");

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.alertDialog_message));
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "onClick: closed dialog");

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN,false);
                    editor.apply();
                       dialog.dismiss();
                }
            });
            alertDialogBuilder.setIcon(R.drawable.background);
            alertDialogBuilder.setTitle(" ");
            AlertDialog alertDialog =alertDialogBuilder.create();
            alertDialog.show();


        }
    }
}