package com.kosalaam.api.modules.accommodation.domain;

import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, UUID> {

    @Query( value =
            "SELECT * " +
                    "FROM accommodation a " +
                    "WHERE GET_DISTANCE(:latitude, :longitude, a.latitude, a.longitude) < :distance " +
//                    ":muslimFriendlyFilter " +
                    "AND a.name LIKE '%'||:keyword||'%'",
            countQuery =
                    "SELECT COUNT(*) " +
                            "FROM accommodation a " +
                            "WHERE GET_DISTANCE(:latitude, :longitude, a.latitude, a.longitude) < :distance " +
//                            ":muslimFriendlyFilter " +
                            "AND a.name LIKE '%'||:keyword||'%'",

            nativeQuery = true )
    Page<Accommodation> findByLocation(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distance") int distance,
            @Param("keyword") String keyword,
//            @Param("muslimFriendlyFilter") String muslimFriendlyFilter,
            Pageable pageable
    );
}
