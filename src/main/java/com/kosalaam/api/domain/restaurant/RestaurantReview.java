package com.kosalaam.api.domain.restaurant;

import javax.persistence.*;

@Table(name="restaurant_review")
@Entity
public class RestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "ko_user_id", nullable = false)
    private Long KoUserId;

    @Column(length = 500, nullable = false)
    private String comment;

}
