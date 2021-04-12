package com.example.sd6501_budgiecoin_project;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

public class BiometricUtilies {

    public static boolean isBiometricPromptEnabled(){
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P);
    }

    public static boolean SDKVersionSupported(){
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    public static boolean HardwareSupported(Context context){
        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
        return fingerprintManager.hasEnrolledFingerprints();
    }

    public static boolean PermissionIsGranten(Context context){
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED;


    }



}
