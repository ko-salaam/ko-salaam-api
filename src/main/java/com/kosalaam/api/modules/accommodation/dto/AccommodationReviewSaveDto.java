package com.kosalaam.api.modules.accommodation.dto;

import com.kosalaam.api.modules.accommodation.domain.AccommodationReview;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AccommodationReviewSaveDto {

    @ApiModelProperty(notes = "숙소 ID")
    private Long accommodationId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;

    /**
     * 숙소 리뷰 DTO 를 Entity 로 convert
     * @param koUserId 사용자 ID
     * @return 리뷰 Entity
     */
    public AccommodationReview toEntity(Long koUserId) {
        return AccommodationReview.builder()
                .accommodationId(accommodationId)
                .koUserId(koUserId)
                .comment(comment)
                .build();
    }
}
