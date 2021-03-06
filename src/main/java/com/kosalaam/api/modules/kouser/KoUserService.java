package com.kosalaam.api.modules.kouser;

import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import com.kosalaam.api.modules.kouser.dto.KoUserDto;
import com.kosalaam.api.modules.restaurant.domain.RestaurantLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log4j2
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
    public KoUserDto signUp(String firebaseUuid) throws Exception {
        if (koUserRepository.findByFirebaseUuid(firebaseUuid).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 Firebase UUID 입니다.");
        }
        KoUser koUser = koUserRepository.save(new KoUser(firebaseUuid));
        koUser.setName("user" + koUser.getId());
        return new KoUserDto(koUser);
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


    @Transactional
    public KoUserDto updateUser(KoUserDto koUserDto, String firebaseUuid) throws Exception {

        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 id 입니다.")
                );
        koUser.update(koUserDto);

        return new KoUserDto(koUser);
    }
}
