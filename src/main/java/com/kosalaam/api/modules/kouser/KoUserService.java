package com.kosalaam.api.modules.kouser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.kosalaam.api.common.AuthUtils;

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


    /**
     * 사용자 정보 조회
     * @param id 사용자 id
     * @return
     * @throws Exception
     */
    @Transactional
    public KoUser getUser(Long id) throws Exception {
        return koUserRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 id 입니다.")
                );
    }

    /**
     * 나의 정보 조회
     * @param firebaseUuid
     * @return
     * @throws Exception
     */
    @Transactional
    public KoUser getMe(String firebaseUuid) throws Exception {
        return koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 token 입니다.")
                );
    }

    /**
     * 회원가입
     * @param firebaseUuid
     * @throws Exception
     */
    @Transactional
    public KoUser signUp(String firebaseUuid) throws Exception {
        if (koUserRepository.findByFirebaseUuid(firebaseUuid).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 Firebase UUID 입니다.");
        }
        return koUserRepository.save(new KoUser(firebaseUuid));
    }

    /**
     * 로그인
     * @param firebaseUuid
     * @throws Exception
     */
    @Transactional
    public KoUser signIn(String firebaseUuid) throws Exception {
        return koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 id 입니다.")
                );
    }

    /**
     * 회원 탈
     * @param firebaseUuid
     * @throws Exception
     */
    @Transactional
    public void deleteUser(String firebaseUuid) throws Exception {

        // Get User
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 id 입니다.")
                );

        // Delete
        restaurantLikeRepository.deleteByKoUserId(koUser.getId());
        koUserRepository.deleteById(koUser.getId());

    }
}
