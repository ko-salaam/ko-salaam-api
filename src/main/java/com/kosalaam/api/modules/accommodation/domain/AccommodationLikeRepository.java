package com.kosalaam.api.modules.accommodation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccommodationLikeRepository extends JpaRepository<AccommodationLike, Long> {

    Optional<AccommodationLike> findByKoUserIdAndAccommodationId(Long koUserId, Long accommodationId);
}
