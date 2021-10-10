package com.kosalaam.api.modules.prayerroom.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class PrayerroomReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prayerroom_id", nullable = false)
    private UUID prayerRoomId;

    @Column(name = "ko_user_id", nullable = false)
    private Long KoUserId;

    @Column(length = 500, nullable = false)
    private String comment;

}
