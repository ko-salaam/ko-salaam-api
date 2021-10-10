package com.kosalaam.api.modules.restaurant.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="restaurant_review")
@Entity
public class RestaurantReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "ko_user_id", nullable = false)
    private Long koUserId;

    @Column(length = 500, nullable = false)
    private String comment;

}
