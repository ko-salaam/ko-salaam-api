package com.kosalaam.api.modules.prayerroom.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PrayerroomReviewRepository extends JpaRepository<PrayerroomReview, Long> {

    List<PrayerroomReview> findByPrayerroomId(UUID id);

}
