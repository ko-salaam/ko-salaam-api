package com.kosalaam.api.modules.kouser.dto;

import com.kosalaam.api.modules.kouser.domain.KoUser;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@ApiModel
public class KoUserDto {

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

    public KoUserDto(KoUser koUser) {
        this.id = koUser.getId();
        this.firebaseUuid = koUser.getFirebaseUuid();
        this.isCertificated = koUser.getIsCertificated();
        this.phoneNumber = koUser.getPhoneNumber();
        this.email = koUser.getEmail();
        this.profileImg = koUser.getProfileImg();
        this.isHost = koUser.getIsHost();
    }
}
