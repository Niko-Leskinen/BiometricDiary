package com.example.fingerprintdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    BiometricActivity biometricActivity;

    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        biometricActivity = new BiometricActivity();

        // Important, check the biometric capability of the device at start
        if (biometricActivity.checkCompatibility(this) == true) {
            // Good
        } else {
            alertDialog();
        }


        button_login = findViewById(R.id.button_logIn);
        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                biometricActivity.biometricPrompt(MainActivity.this);
            }
        });

    }

    public void alertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(R.string.error_message_no_biometrics).setCancelable(false)
                .setPositiveButton(R.string.error_message_exit,
                        (dialog, id) -> finish());

        AlertDialog alert = builder.create();
        alert.show();

    }
}