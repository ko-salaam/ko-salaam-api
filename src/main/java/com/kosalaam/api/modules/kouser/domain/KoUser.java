package com.kosalaam.api.modules.kouser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
