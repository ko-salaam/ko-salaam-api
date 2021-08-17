package com.kosalaam.api.modules.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="restaurant_like")
@Entity
public class RestaurantLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(name="restaurant_id")
    private Long restaurantId;

    @Column(name="ko_user_id")
    private Long koUserId;

    public RestaurantLike(Long koUserId, Long restaurantId) {
        this.koUserId = koUserId;
        this.restaurantId = restaurantId;
    }
}
