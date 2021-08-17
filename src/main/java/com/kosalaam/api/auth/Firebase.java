package com.kosalaam.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component
public class Firebase {

    public void initFireBaseSDK() throws Exception {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(getGoogleCredentials())
                .build();

        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        if (firebaseApps.isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

    }

    public GoogleCredentials getGoogleCredentials() throws Exception {

        // Firebase Admin SDK 용 credential json
        HashMap<String, String> firebaseCredential = new HashMap<String, String>();
        firebaseCredential.put("type", "service_account");
        firebaseCredential.put("project_id", System.getenv("FB_PROJECT_ID"));
        firebaseCredential.put("private_key_id", System.getenv("FB_PRIVATE_KEY_ID"));
        firebaseCredential.put("private_key", System.getenv("FB_PRIVATE_KEY").replace("\\n", "\n"));
        firebaseCredential.put("client_email", System.getenv("FB_CLIENT_EMAIL"));
        firebaseCredential.put("client_id", System.getenv("FB_CLIENT_ID"));
        firebaseCredential.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
        firebaseCredential.put("token_uri", "https://oauth2.googleapis.com/token");
        firebaseCredential.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
        firebaseCredential.put("client_x509_cert_url", System.getenv("FB_CLIENT_X509_CERT_URL"));

        // Object to json string
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredential);

        return GoogleCredentials.fromStream(IOUtils.toInputStream(jsonString));
    }

    public String checkToken(String token) throws Exception {
        initFireBaseSDK();
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        String uuid = decodedToken.getUid();

        return uuid;
    }

}
