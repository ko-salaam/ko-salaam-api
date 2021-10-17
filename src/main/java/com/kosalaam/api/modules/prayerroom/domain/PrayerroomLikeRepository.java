package com.kosalaam.api.modules.prayerroom.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrayerroomLikeRepository extends JpaRepository<PrayerroomLike, Long> {

    Optional<PrayerroomLike> findByKoUserIdAndPrayerroomId(Long koUserId, UUID PrayerroomId);

    Long deleteByKoUserId(Long koUserId);
}
