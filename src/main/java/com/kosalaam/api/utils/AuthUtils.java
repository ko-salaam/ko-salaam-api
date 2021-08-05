package com.kosalaam.api.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import java.io.FileInputStream;
import java.util.List;

public class AuthUtils {

    public static void initFireBaseSDK() throws Exception {

        FileInputStream serviceAccount =
                new FileInputStream("");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        if (firebaseApps == null) {
            FirebaseApp.initializeApp(options);
        }

        UserRecord userRecord = FirebaseAuth.getInstance().getUser("");
        System.out.println(userRecord.getEmail());
    }

}
