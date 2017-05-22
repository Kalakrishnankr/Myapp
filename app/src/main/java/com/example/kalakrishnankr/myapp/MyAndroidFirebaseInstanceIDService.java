package com.example.kalakrishnankr.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by kalakrishnan.kr on 22/5/17.
 */
public class MyAndroidFirebaseInstanceIDService extends Service {

    @Nullable

    private static final String TAG = "MyAndroidFCMIIDService";

    public void onTokenRefresh() {
        //Get hold of the registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log the token
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
    private void sendRegistrationToServer(String token) {
        //Implement this method if you want to store the token on your server
    }
    public IBinder onBind(Intent intent) {
        return null;
    }
}
