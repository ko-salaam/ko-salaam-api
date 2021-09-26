package com.kosalaam.api.modules.accommodation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="accommodation_review")
@Entity
public class AccommodationReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accommodation_id", nullable = false)
    private Long accommodationId;

    @Column(name = "ko_user_id", nullable = false)
    private Long koUserId;

    @Column(length = 500, nullable = false)
    private String comment;
}
