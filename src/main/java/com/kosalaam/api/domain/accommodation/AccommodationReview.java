package com.kosalaam.api.domain.accommodation;

import javax.persistence.*;

@Table(name="accommodation_review")
public class AccommodationReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accommodation_id", nullable = false)
    private Long accommodationId;

    @Column(name = "ko_user_id", nullable = false)
    private Long KoUserId;

    @Column(length = 500, nullable = false)
    private String comment;
}
