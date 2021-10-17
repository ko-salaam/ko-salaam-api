package com.kosalaam.api.modules.prayerroom.domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrayerroomRepository extends JpaRepository<Prayerroom, UUID> {

    @Query( value =
            "SELECT * " +
            "FROM Prayerroom p " +
            "WHERE GET_DISTANCE(:latitude, :longitude, p.latitude, p.longitude) < :distance " +
                "AND p.name LIKE '%'||:keyword||'%'",
            countQuery =
            "SELECT COUNT(*) " +
            "FROM Prayerroom p " +
            "WHERE GET_DISTANCE(:latitude, :longitude, p.latitude, p.longitude) < :distance " +
                "AND p.name LIKE '%'||:keyword||'%'",
            nativeQuery = true )
    Page<Prayerroom> findByLocation(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distance") int distance,
            @Param("keyword") String keyword,
            Pageable pageable
    );

}
