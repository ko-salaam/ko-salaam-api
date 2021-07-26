package com.kosalaam.api.modules.restaurant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel
public class RestaurantReviewRespDto {

    @ApiModelProperty(notes = "식당 리뷰 ID")
    private Long id;

    @ApiModelProperty(notes = "식당 ID")
    private Long restaurantId;

    @ApiModelProperty(notes = "사용자 ID")
    private Long KoUserId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;
}
