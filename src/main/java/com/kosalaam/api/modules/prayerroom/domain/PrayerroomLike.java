package com.kosalaam.api.modules.prayerroom.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Prayerroom_like")
@Entity
public class PrayerroomLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(name="prayerroom_id")
    private UUID prayerroomId;

    @Column(name="ko_user_id")
    private Long koUserId;

    public PrayerroomLike(Long koUserId, UUID prayerroomId) {
        this.koUserId = koUserId;
        this.prayerroomId = prayerroomId;
    }
}
