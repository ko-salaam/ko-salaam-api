package com.kosalaam.api.modules.accommodation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccommodationReviewRepository extends JpaRepository<AccommodationReview, Long> {
    List<AccommodationReview> findByAccommodationId(UUID id);
}
