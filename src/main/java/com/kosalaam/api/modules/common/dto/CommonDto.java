package com.kosalaam.api.modules.common.dto;

import com.kosalaam.api.modules.common.domain.PraySupplies;
import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
@ApiModel
public class CommonDto {

    @ApiModelProperty(notes = "ID", position = 1)
    private Long id;

    @ApiModelProperty(notes = "전체 UUID", position = 2)
    private UUID uuid;

    @ApiModelProperty(notes = "장소 종류(식당, 숙박, 기도실)", position = 3)
    private PlaceType placeType;

    @ApiModelProperty(notes = "이름", position = 3)
    private String name;

    @ApiModelProperty(notes = "위도", position = 4)
    private double latitude;

    @ApiModelProperty(notes = "경도", position = 5)
    private double longitude;

    @ApiModelProperty(notes = "상세 주소", position = 6)
    private String address;

    @ApiModelProperty(notes = "전화번호", position = 7)
    private String phoneNumber;

    @ApiModelProperty(notes = "사진 ID", position = 8)
    private String imagesId;

    @ApiModelProperty(notes = "요리 분류", position = 9)
    private String dishType;

    @ApiModelProperty(notes = "무슬림 친화", position = 10)
    private MuslimFriendlies muslimFriendly;

    @ApiModelProperty(notes = "좋아요 수", position = 11)
    private int likedCount;

    @ApiModelProperty(notes = "영업 시간", position = 12)
    private String openingHours;

    @ApiModelProperty(notes = "주차장 여부", position = 13)
    private Boolean isParkingLot;

    @ApiModelProperty(notes = "비고", position = 14)
    private String detailInfo;

    @ApiModelProperty(notes = "좋아요 여부", position = 15)
    private Boolean isLiked;

    // 숙박
    @ApiModelProperty(notes = "무슬림 친화 여부", position = 11)
    private Boolean isMuslimFriendly;

    // 숙박, 기도실
    @ApiModelProperty(notes = "기도 물품 구비 여부", position = 13)
    private PraySupplies praySupplies;

    // 기도실
    @ApiModelProperty(notes = "코살람 호스팅 기도실 여부", position = 12)
    private Boolean isKosalaamRoom;

    @ApiModelProperty(notes = "호스트 ID", position = 13)
    private Long hostId;

    @ApiModelProperty(notes = "이용 가격", position = 14)
    private int price;

}
