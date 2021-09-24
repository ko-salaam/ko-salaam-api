package com.kosalaam.api.modules.accommodation.dto;

import com.kosalaam.api.modules.accommodation.domain.Accommodation;
import com.kosalaam.api.modules.common.domain.PraySupplies;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
@ApiModel
public class AccommodationDto {

    @ApiModelProperty(notes = "숙소 ID", position = 1)
    private final Long id;

    @ApiModelProperty(notes = "UUID", position = 2)
    private final UUID uuid;

    @ApiModelProperty(notes = "이름", position = 3)
    private final String name;

    @ApiModelProperty(notes = "위도", position = 4)
    private final double latitude;

    @ApiModelProperty(notes = "경도", position = 5)
    private final double longitude;

    @ApiModelProperty(notes = "상세 주소", position = 6)
    private final String address;

    @ApiModelProperty(notes = "전화번호", position = 7)
    private final String phoneNumber;

    @ApiModelProperty(notes = "사진 ID", position = 8)
    private final Long imagesId;

    @ApiModelProperty(notes = "좋아요 수", position = 9)
    private final int likedCount;

    @ApiModelProperty(notes = "영업 시간", position = 10)
    private final String openingHours;

    @ApiModelProperty(notes = "주차장 여부", position = 11)
    private final Boolean isParkingLot;

    @ApiModelProperty(notes = "무슬림 친화 여부", position = 12)
    private final Boolean isMuslimFriendly;

    @ApiModelProperty(notes = "기도 물품 구비 여부", position = 13)
    private final PraySupplies praySupplies;

    @ApiModelProperty(notes = "비고", position = 14)
    private final String detailInfo;

    @ApiModelProperty(notes = "좋아요 여부", position = 15)
    private final Boolean isLiked;

    /**
     * Entity to DTO
     * @param entity 숙소 Entity
     */
    public AccommodationDto(Accommodation entity) {
        this.id = entity.getId();
        this.uuid = entity.getUuid();
        this.name = entity.getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.imagesId = entity.getImagesId();
        this.likedCount = entity.getLikedCount();
        this.openingHours = entity.getOpeningHours();
        this.isParkingLot = entity.getIsParkingLot();
        this.isMuslimFriendly = entity.getIsMuslimFriendly();
        this.praySupplies = new PraySupplies(
                entity.getIsKoran(),
                entity.getIsMat(),
                entity.getIsQibla(),
                entity.getIsWashingRoom()
                );
        this.detailInfo = entity.getDetailInfo();
        this.isLiked = Boolean.FALSE;
    }

    /**
     * DTO to Entity
     * @return 숙소 Entity
     */
    public Accommodation toEntity() {
        return Accommodation.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .imagesId(imagesId)
                .likedCount(likedCount)
                .openingHours(openingHours)
                .isParkingLot(isParkingLot)
                .isMuslimFriendly(isMuslimFriendly)
                .isKoran(praySupplies.getIsKoran())
                .isMat(praySupplies.getIsMat())
                .isQibla(praySupplies.getIsQibla())
                .isWashingRoom(praySupplies.getIsWashingRoom())
                .detailInfo(detailInfo)
                .build();
    }
}
