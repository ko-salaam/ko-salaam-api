package com.kosalaam.api.modules.prayerroom.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="Prayerroom_review")
@Entity
public class PrayerroomReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prayerroom_id", nullable = false)
    private UUID prayerroomId;

    @Column(name = "ko_user_id", nullable = false)
    private Long koUserId;

    @Column(length = 500, nullable = false)
    private String comment;

}
