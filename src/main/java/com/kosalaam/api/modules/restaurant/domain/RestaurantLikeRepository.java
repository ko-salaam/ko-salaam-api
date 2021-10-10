package com.kosalaam.api.modules.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantLikeRepository extends JpaRepository<RestaurantLike, Long> {

    Optional<RestaurantLike> findByKoUserIdAndRestaurantId(Long koUserId, UUID restaurantId);

    Long deleteByKoUserId(Long koUserId);
}
