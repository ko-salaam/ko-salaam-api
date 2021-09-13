package com.kosalaam.api.modules.restaurant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class RestaurantReviewsDto {

    @ApiModelProperty(notes = "식당 리뷰 개수")
    private Integer reviewCnt;

    @ApiModelProperty(notes = "식당 리뷰 리스트")
    private List<RestaurantReviewDto> restaurantReviews;

}
