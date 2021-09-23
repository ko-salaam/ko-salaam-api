package com.kosalaam.api.modules.prayerroom.dto;

import com.kosalaam.api.modules.common.domain.PraySupplies;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel
public class PrayerroomRespDto {

    @ApiModelProperty(notes = "기도실 ID", position = 1)
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

    @ApiModelProperty(notes = "휴일", position = 9)
    private String holiday;

    @ApiModelProperty(notes = "주차장 여부", position = 10)
    private Boolean isParkingLot;

    @ApiModelProperty(notes = "기도실 물품 구비 여부", position = 11)
    private PraySupplies praySupplies;

    @ApiModelProperty(notes = "코살람 호스팅 기도실 여부", position = 12)
    private Boolean isKosalaamRoom;

    @ApiModelProperty(notes = "호스트 ID", position = 13)
    private Long hostId;

    @ApiModelProperty(notes = "이용 가격", position = 14)
    private int price;

    @ApiModelProperty(notes = "비고", position = 15)
    private String detailInfo;
}
