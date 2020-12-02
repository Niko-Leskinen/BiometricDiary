package com.example.fingerprintdiary;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;

public class BiometricActivity {

    private androidx.biometric.BiometricManager biometricManager;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public boolean checkCompatibility (Context context) {

        biometricManager = androidx.biometric.BiometricManager.from(context);

        if (biometricManager.canAuthenticate() == biometricManager.BIOMETRIC_SUCCESS) {
            return true;
        } else {
            return false;
        }
    }

    public void biometricPrompt(Context context) {

        executor = ContextCompat.getMainExecutor(context);
        biometricPrompt = new BiometricPrompt((FragmentActivity) context, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(context,
                        "Authentication Error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(context,
                        "Authentication Succeeded", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DiaryActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Authentication Failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Log In")
                .setSubtitle("Log In Using Your Fingerprint")
                .setNegativeButtonText(("Cancel"))
                .build();

        biometricPrompt.authenticate(promptInfo);
    }
}