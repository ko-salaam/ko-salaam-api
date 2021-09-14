package com.kosalaam.api.modules.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    String HAVERSINE_PART =
            ":arcHav * cos(radians(r.latitude)) * cos(radians(r.longitude) " +
            "- :longToRad " +
            "+ :ladToSin * sin(radians(r.latitude))";
    @Query( value = "SELECT r FROM restaurant r WHERE " + HAVERSINE_PART + " < :distance ORDER BY " + HAVERSINE_PART,
            nativeQuery = true)
    List<Restaurant> findByLocation(
            @Param("arcHav") double arcHav,
            @Param("longToRad") double longToRad,
            @Param("ladToSin") double ladToSin,
            @Param("distance") int distance
    );

    @Query( value = "SELECT * FROM restaurant r WHERE GET_DISTANCE(:latitude, :longitude, r.latitude, r.longitude) < :distance",
            nativeQuery = true)
    List<Restaurant> findByTest(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distance") int distance
    );



}
