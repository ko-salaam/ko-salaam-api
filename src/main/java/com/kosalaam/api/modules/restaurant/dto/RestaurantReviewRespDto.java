package com.kosalaam.api.modules.restaurant.dto;

import com.kosalaam.api.modules.restaurant.domain.RestaurantReview;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@ApiModel
public class RestaurantReviewRespDto {

    @ApiModelProperty(notes = "식당 리뷰 ID")
    private Long id;

    @ApiModelProperty(notes = "식당 ID")
    private UUID restaurantId;

    @ApiModelProperty(notes = "사용자 ID")
    private Long koUserId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;

    public RestaurantReviewRespDto(RestaurantReview entity) {
        this.id = entity.getId();
        this.restaurantId = entity.getRestaurantId();
        this.koUserId = entity.getKoUserId();
        this.comment = entity.getComment();
    }
}
