package com.kosalaam.api.modules.accommodation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel
public class AccommodationReviewRespDto {

    @ApiModelProperty(notes = "숙소 리뷰 ID")
    private Long id;

    @ApiModelProperty(notes = "숙소 ID")
    private Long AccommodationId;

    @ApiModelProperty(notes = "사용자 ID")
    private Long KoUserId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;
}
