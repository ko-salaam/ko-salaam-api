package com.kosalaam.api.modules.accommodation.domain;

import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query( value =
            "SELECT * " +
                    "FROM accommodation r " +
                    "WHERE GET_DISTANCE(:latitude, :longitude, r.latitude, r.longitude) < :distance " +
                    "AND r.name LIKE '%'||:keyword||'%'",
            countQuery =
                    "SELECT COUNT(*) " +
                            "FROM accommodation r " +
                            "WHERE GET_DISTANCE(:latitude, :longitude, r.latitude, r.longitude) < :distance " +
                            "AND r.name LIKE '%'||:keyword||'%'",
            nativeQuery = true )
    Page<Accommodation> findByLocation(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distance") int distance,
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
