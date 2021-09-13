package com.kosalaam.api.modules.restaurant.dto;

import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantSaveDto {

    @ApiModelProperty(notes = "이름", position = 1)
    private String name;

    @ApiModelProperty(notes = "위도", position = 2)
    private double latitude;

    @ApiModelProperty(notes = "경도", position = 3)
    private double longitude;

    @ApiModelProperty(notes = "상세 주소", position = 4)
    private String address;

    @ApiModelProperty(notes = "전화번호", position = 5)
    private String phoneNumber;

    @ApiModelProperty(notes = "사진 ID", position = 6)
    private String imagesId;

    @ApiModelProperty(notes = "요리 분류", position = 7)
    private String dishType;

    @ApiModelProperty(notes = "무슬림 친화", position = 8)
    private MuslimFriendlies muslimFriendly;

    @ApiModelProperty(notes = "좋아요 수", position = 9)
    private int likedCount;

    @ApiModelProperty(notes = "영업 시간", position = 10)
    private String openingHours;

    @ApiModelProperty(notes = "휴일", position = 11)
    private String holiday;

    @ApiModelProperty(notes = "주차장 여부", position = 12)
    private Boolean isParkingLot;

    @ApiModelProperty(notes = "비고", position = 13)
    private String detailInfo;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .imagesId(imagesId)
                .dishType(dishType)
                .muslimFriendly(muslimFriendly)
                .likedCount(likedCount)
                .openingHours(openingHours)
                .holiday(holiday)
                .isParkingLot(isParkingLot)
                .detailInfo(detailInfo)
                .build();
    }
}
