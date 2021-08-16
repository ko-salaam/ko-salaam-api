package com.kosalaam.api.modules.kouser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.kosalaam.api.auth.Firebase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KoUserService {

    private final Firebase authUtils;

    public void getUser(String uid) throws Exception {

        authUtils.initFireBaseSDK();

        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
        System.out.println(userRecord.getEmail());
    }
}
