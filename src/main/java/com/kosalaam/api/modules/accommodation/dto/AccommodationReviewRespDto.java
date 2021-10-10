package com.kosalaam.api.modules.accommodation.dto;

import com.kosalaam.api.modules.accommodation.domain.AccommodationReview;
import com.kosalaam.api.modules.restaurant.domain.RestaurantReview;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@ApiModel
public class AccommodationReviewRespDto {

    @ApiModelProperty(notes = "숙소 리뷰 ID")
    private Long id;

    @ApiModelProperty(notes = "숙소 ID")
    private UUID accommodationId;

    @ApiModelProperty(notes = "사용자 ID")
    private Long koUserId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;

    public AccommodationReviewRespDto(AccommodationReview entity) {
        this.id = entity.getId();
        this.accommodationId = entity.getAccommodationId();
        this.koUserId = entity.getKoUserId();
        this.comment = entity.getComment();
    }
}
