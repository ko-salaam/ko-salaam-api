package com.kosalaam.api.modules.restaurant.dto;

import com.kosalaam.api.modules.restaurant.domain.RestaurantReview;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.UUID;

@Getter // swagger ui에서 필요
public class RestaurantReviewSaveDto {

    @ApiModelProperty(notes = "식당 ID")
    private UUID restaurantId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;

    /**
     * 식당 리뷰 DTO 를 Entity 로 convert
     * @return RestaurantReview 객체
     */
    public RestaurantReview toEntity(Long koUserId) {
        return RestaurantReview.builder()
                .restaurantId(restaurantId)
                .koUserId(koUserId)
                .comment(comment)
                .build();
    }

}
