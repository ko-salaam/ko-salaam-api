package com.kosalaam.api.modules.restaurant.dto;

import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@ApiModel
public class RestaurantRespDto {

    @ApiModelProperty(notes = "식당 ID", position = 1)
    private Long id;

    @ApiModelProperty(notes = "이름", position = 2)
    private String name;

    @ApiModelProperty(notes = "위도", position = 3)
    private double latitude;

    @ApiModelProperty(notes = "경도", position = 4)
    private double longitude;

    @ApiModelProperty(notes = "상세 주소", position = 5)
    private String address;

    @ApiModelProperty(notes = "전화번호", position = 6)
    private String phoneNumber;

    @ApiModelProperty(notes = "사진 ID", position = 7)
    private String imagesId;

    @ApiModelProperty(notes = "요리 분류", position = 8)
    private String dishType;

    @ApiModelProperty(notes = "무슬림 친화", position = 8)
    private MuslimFriendlies muslimFriendly;

    @ApiModelProperty(notes = "좋아요 수", position = 8)
    private int likedCount;

    @ApiModelProperty(notes = "영업 시간", position = 10)
    private String openingHours;

    @ApiModelProperty(notes = "주차장 여부", position = 11)
    private Boolean isParkingLot;

    @ApiModelProperty(notes = "비고", position = 12)
    private String detailInfo;

    public RestaurantRespDto(Restaurant entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.imagesId = entity.getImagesId();
        this.dishType = entity.getDishType();
        this.muslimFriendly = entity.getMuslimFriendly();
        this.likedCount = entity.getLikedCount();
        this.openingHours = entity.getOpeningHours();
        this.isParkingLot = entity.getIsParkingLot();
        this.detailInfo = entity.getDetailInfo();
    }
}
