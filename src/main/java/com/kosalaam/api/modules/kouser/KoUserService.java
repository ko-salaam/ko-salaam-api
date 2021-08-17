package com.kosalaam.api.modules.kouser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.kosalaam.api.auth.Firebase;

import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KoUserService {

    private final KoUserRepository koUserRepository;

    private final Firebase firebase;

    public void getUser(String uid) throws Exception {

        firebase.initFireBaseSDK();

        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);

    }
    
    public void signUp(String token) throws Exception {

        // token 체크
        String firebaseUuid = firebase.checkToken(token);
        if ( koUserRepository.findByFirebaseUuid(firebaseUuid).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 Firebase UUID입니다.");
        }

        // insert DB
        koUserRepository.save(new KoUser(firebaseUuid));

        }

        public void signIn(String token) throws Exception {

        // user 여부 체크
        String firebaseUuid = firebase.checkToken(token);
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+token+"' 는 존재하지 않는 사용자입니다."
                ));
    }
}
