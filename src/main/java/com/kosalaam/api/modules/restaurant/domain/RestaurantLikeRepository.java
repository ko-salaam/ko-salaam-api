package com.kosalaam.api.modules.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantLikeRepository extends JpaRepository<RestaurantLike, Long> {

    public Optional<RestaurantLike> findByKoUserIdAndRestaurantId(Long koUserId, Long restaurantId);

}
