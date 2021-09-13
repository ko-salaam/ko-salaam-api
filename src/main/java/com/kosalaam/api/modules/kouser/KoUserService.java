package com.kosalaam.api.modules.kouser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.kosalaam.api.common.FirebaseUtils;

import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import com.kosalaam.api.modules.restaurant.domain.RestaurantLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KoUserService {

    private final KoUserRepository koUserRepository;

    private final RestaurantLikeRepository restaurantLikeRepository;


    private final FirebaseUtils firebaseUtils;

    public void getUser(String uid) throws Exception {

        firebaseUtils.initFireBaseSDK();

        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);

    }

    @Transactional
    public void signUp(String token) throws Exception {

        // token 체크
        String firebaseUuid = firebaseUtils.checkToken(token);
        if (koUserRepository.findByFirebaseUuid(firebaseUuid) != null) {
            throw new IllegalArgumentException("이미 존재하는 Firebase UUID 입니다.");
        }

        // insert DB
        koUserRepository.save(new KoUser(firebaseUuid));

    }

    @Transactional
    public void signIn(String token) throws Exception {

        // user 여부 체크
        KoUser koUser = firebaseUtils.getKoUser(token);

    }

    @Transactional
    public void deleteUser(String token) throws Exception {
        KoUser koUser = firebaseUtils.getKoUser(token);
        restaurantLikeRepository.deleteByKoUserId(koUser.getId());
        koUserRepository.deleteById(koUser.getId());
    }
}
