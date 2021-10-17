package com.kosalaam.api.modules.restaurant.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    @Query( value =
            "SELECT * " +
            "FROM restaurant r " +
            "WHERE GET_DISTANCE(:latitude, :longitude, r.latitude, r.longitude) < :distance " +
//            ":muslimFriendlyFilter " +
            "AND r.name LIKE '%'||:keyword||'%'",
            countQuery =
            "SELECT COUNT(*) " +
            "FROM restaurant r " +
            "WHERE GET_DISTANCE(:latitude, :longitude, r.latitude, r.longitude) < :distance " +
//            ":muslimFriendlyFilter " +
            "AND r.name LIKE '%'||:keyword||'%'",
            nativeQuery = true )
    Page<Restaurant> findByLocation(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distance") int distance,
            @Param("keyword") String keyword,
//            @Param("muslimFriendlyFilter") String muslimFriendlyFilter,
            Pageable pageable
    );

}
