package com.kosalaam.api.modules.restaurant.dto;

import com.kosalaam.api.modules.place.domain.Place;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.place.dto.PlaceType;
import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.UUID;

@Getter
@ApiModel
public class RestaurantDto extends PlaceDto {

    @ApiModelProperty(notes = "요리 분류", position = 9)
    private String dishType;

    @ApiModelProperty(notes = "무슬림 친화도", position = 10)
    private MuslimFriendlies muslimFriendly;

    /**
     * Entity To DTO
     * @param entity 식당 Entity
     */
    public RestaurantDto(Restaurant entity) {
        this.id = entity.getId();
        this.placeType = PlaceType.RESTAURANT;
        this.name = entity.getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.images = entity.getImages();
        this.dishType = entity.getDishType();
        this.muslimFriendly = entity.getMuslimFriendly();
        if (entity.getMuslimFriendly() == null) {
            this.muslimFriendly = MuslimFriendlies.NONE;
        }
        this.likedCount = entity.getLikedCount();
        this.openingHours = entity.getOpeningHours();
        this.isParkingLot = entity.getIsParkingLot();
        this.detailInfo = entity.getDetailInfo();
        this.isLiked = Boolean.FALSE;
    }

    /**
     * DTO to Entity
     * @return 식당 Entity
     */
    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .images(images)
                .dishType(dishType)
                .muslimFriendly(muslimFriendly)
                .likedCount(likedCount)
                .openingHours(openingHours)
                .isParkingLot(isParkingLot)
                .detailInfo(detailInfo)
                .build();
    }
}
