package com.kosalaam.api.web.prayerroom.dto;

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

    @ApiModelProperty(notes = "코란 여부", position = 13)
    private Boolean isKoran;

    @ApiModelProperty(notes = "매트 여부", position = 14)
    private Boolean isMat;

    @ApiModelProperty(notes = "기도실 의상 여부", position = 15)
    private Boolean isClothes;

    @ApiModelProperty(notes = "키블라 여부", position = 16)
    private Boolean isQibla;

    @ApiModelProperty(notes = "세족실 여부", position = 17)
    private Boolean isWashingRoom;

    @ApiModelProperty(notes = "코살람 호스팅 기도실 여부", position = 2)
    private Boolean isKosalaamRoom;

    @ApiModelProperty(notes = "호스트 ID", position = 3)
    private Long hostId;

    @ApiModelProperty(notes = "이용 가격", position = 3)
    private int price;

    @ApiModelProperty(notes = "비고", position = 18)
    private String detailInfo;
}
