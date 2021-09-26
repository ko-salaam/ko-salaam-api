package com.kosalaam.api.modules.accommodation.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodation_like")
@Entity
public class AccommodationLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="accommodation_id")
    private Long accommodationId;

    @Column(name="ko_user_id")
    private Long koUserId;

    public AccommodationLike(Long accommodationId, Long koUserId) {
        this.accommodationId = accommodationId;
        this.koUserId = koUserId;
    }
}
