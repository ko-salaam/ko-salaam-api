package com.kosalaam.api.modules.accommodation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@ApiModel
public class AccommodationReviewsRespDto {

    @ApiModelProperty(notes = "숙소 리뷰 개수")
    private Integer reviewCnt;

    @ApiModelProperty(notes = "숙소 리뷰 리스트")
    private List<AccommodationReviewRespDto> reviews;
}
