package com.kosalaam.api.modules.accommodation.dto;

import com.kosalaam.api.modules.accommodation.domain.Accommodation;
import com.kosalaam.api.modules.common.PraySupplies;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel
public class AccommodationRespDto {

    @ApiModelProperty(notes = "숙소 ID", position = 1)
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
    private Long imagesId;

    @ApiModelProperty(notes = "좋아요 수", position = 8)
    private int likedCount;

    @ApiModelProperty(notes = "영업 시간", position = 9)
    private String openingHours;

    @ApiModelProperty(notes = "주차장 여부", position = 10)
    private Boolean isParkingLot;

    @ApiModelProperty(notes = "무슬림 친화 여부", position = 11)
    private Boolean isMuslimFriendly;

    @ApiModelProperty(notes = "기도실 여부", position = 12)
    private Boolean isPrayerRoom;

    @ApiModelProperty(notes = "기도 물품 구비 여부", position = 13)
    private PraySupplies praySupplies;

    @ApiModelProperty(notes = "비고", position = 14)
    private String detailInfo;

    public AccommodationRespDto(Accommodation entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.imagesId = entity.getImagesId();
        this.likedCount = entity.getLikedCount();
        this.isMuslimFriendly = entity.getIsMuslimFriendly();
        this.detailInfo = entity.getDetailInfo();
    }
}
