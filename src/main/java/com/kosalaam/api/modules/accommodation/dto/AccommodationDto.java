package com.kosalaam.api.modules.accommodation.dto;

import com.kosalaam.api.modules.accommodation.domain.Accommodation;
import com.kosalaam.api.modules.place.domain.PraySupplies;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.place.dto.PlaceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel
public class AccommodationDto extends PlaceDto {

    @ApiModelProperty(notes = "무슬림 친화 여부", position = 12)
    private final Boolean isMuslimFriendly;

    @ApiModelProperty(notes = "기도 물품 구비 여부", position = 13)
    private final PraySupplies praySupplies;


    /**
     * Entity to DTO
     * @param entity 숙소 Entity
     */
    public AccommodationDto(Accommodation entity) {
        this.id = entity.getId();
        this.placeType = PlaceType.ACCOMMODATION;
        this.name = entity.getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.images = entity.getImages();
        this.likedCount = entity.getLikedCount();
        this.openingHours = entity.getCheckInTime() + " ~ " + entity.getCheckOutTime();
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
                .images(images)
                .likedCount(likedCount)
                .isParkingLot(isParkingLot)
                .isMuslimFriendly(isMuslimFriendly)
                .isKoran(praySupplies.getIsKoran())
                .isMat(praySupplies.getIsMat())
                .isQibla(praySupplies.getIsQibla())
                .isWashingRoom(praySupplies.getIsWashingRoom())
                .detailInfo(detailInfo)
                .build();
    }

    /**
     * 좋아요 여부 setter
     * @param isLiked 좋아요 여부
     */
    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }
}
