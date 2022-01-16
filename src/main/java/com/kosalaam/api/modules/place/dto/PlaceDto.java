package com.kosalaam.api.modules.place.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@Setter
@ApiModel
public class PlaceDto {

    @ApiModelProperty(notes = "전체 UUID", position = 1)
    public UUID id;

    @ApiModelProperty(notes = "장소 종류(식당, 숙박, 기도실)", position = 2)
    public PlaceType placeType;

    @ApiModelProperty(notes = "이름", position = 3)
    public String name;

    @ApiModelProperty(notes = "위도", position = 4)
    public double latitude;

    @ApiModelProperty(notes = "경도", position = 5)
    public double longitude;

    @ApiModelProperty(notes = "상세 주소", position = 6)
    public String address;

    @ApiModelProperty(notes = "전화번호", position = 7)
    public String phoneNumber;

    @ApiModelProperty(notes = "사진 리스트", position = 8)
    public String[] images;

    @ApiModelProperty(notes = "좋아요 수", position = 11)
    public int likedCount;

    @ApiModelProperty(notes = "영업 시간", position = 12)
    public String openingHours;

    @ApiModelProperty(notes = "주차장 여부", position = 13)
    public Boolean isParkingLot;

    @ApiModelProperty(notes = "비고", position = 14)
    public String detailInfo;

    @ApiModelProperty(notes = "좋아요 여부", position = 15)
    public Boolean isLiked;

    public PlaceDto toLimitedImages() {
        if (images != null) {
            this.images = Arrays.copyOfRange(images, 0, 4);
        }
        return this;
    }
}
