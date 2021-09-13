package com.kosalaam.api.modules.restaurant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@ApiModel
public class RestaurantReviewsRespDto {

    @ApiModelProperty(notes = "식당 리뷰 개수")
    private Integer reviewCnt;

    @ApiModelProperty(notes = "식당 리뷰 리스트")
    private List<RestaurantReviewRespDto> restaurantReviews;

}
