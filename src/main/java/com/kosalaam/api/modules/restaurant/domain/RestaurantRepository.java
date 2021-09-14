package com.kosalaam.api.modules.restaurant.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query( value = "SELECT * FROM restaurant r WHERE GET_DISTANCE(:latitude, :longitude, r.latitude, r.longitude) < :distance",
            nativeQuery = true)
    List<Restaurant> findByLocation(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distance") int distance,
            Pageable pageable
    );



}
