package com.kosalaam.api.modules.kouser.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.kosalaam.api.modules.kouser.dto.KoUserDto;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ko_user")
@Entity
public class KoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firebase_uuid")
    private String firebaseUuid;

    @Column(name="is_certificated")
    private Boolean isCertificated;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column
    private String email;

    @Column(name="profile_img")
    private String profileImg;

    @Column(name="is_host")
    private Boolean isHost;

    public KoUser(String firebaseUuid) throws Exception {

        UserRecord userRecord = FirebaseAuth.getInstance().getUser(firebaseUuid);

        this.firebaseUuid = firebaseUuid;
        this.isCertificated = Boolean.FALSE;
        this.isHost = Boolean.FALSE;
        this.phoneNumber = userRecord.getPhoneNumber();
        this.email = userRecord.getEmail();
        this.profileImg = userRecord.getPhotoUrl();

    }

    /**
     * update KoUser
     * @param koUserDto DTO
     * @return Entity
     * @throws Exception
     */
    public KoUser update(KoUserDto koUserDto) {
        this.firebaseUuid = Optional.ofNullable(koUserDto.getFirebaseUuid()).orElse(firebaseUuid);
        this.isCertificated = Optional.ofNullable(koUserDto.getIsCertificated()).orElse(isCertificated);
        this.isHost = Optional.ofNullable(koUserDto.getIsHost()).orElse(isHost);
        this.phoneNumber = Optional.ofNullable(koUserDto.getPhoneNumber()).orElse(phoneNumber);
        this.email = Optional.ofNullable(koUserDto.getEmail()).orElse(email);
        this.profileImg = Optional.ofNullable(koUserDto.getProfileImg()).orElse(profileImg);
        return this;
    }
}
