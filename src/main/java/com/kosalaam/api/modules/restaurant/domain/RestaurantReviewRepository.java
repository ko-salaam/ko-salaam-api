package com.kosalaam.api.modules.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {

    List<RestaurantReview> findByRestaurantId(Long id);

}
