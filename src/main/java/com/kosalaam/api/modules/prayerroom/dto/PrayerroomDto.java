package com.kosalaam.api.modules.prayerroom.dto;


import com.kosalaam.api.modules.place.domain.PraySupplies;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.place.dto.PlaceType;
import com.kosalaam.api.modules.prayerroom.domain.Prayerroom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PrayerroomDto extends PlaceDto {

    @ApiModelProperty(notes = "코살람 등록 기도실 여부", position = 2)
    private Boolean isKosalaamRoom;

    @ApiModelProperty(notes = "가격", position = 14)
    private Integer price;

    @ApiModelProperty(notes = "기도실 운영 타입", position = 14)
    private String managingType;

    @ApiModelProperty(notes = "기도 물품 구비 여부", position = 15)
    private PraySupplies praySupplies;

    @ApiModelProperty(notes = "호스트 ID", position = 16)
    private Long hostId;

    /**
     * Entity To DTO
     * @param entity 기도실 Entity
     */
    public PrayerroomDto(Prayerroom entity) {
        this.id = entity.getId();
        this.placeType = PlaceType.PRAYER_ROOM;
        this.isKosalaamRoom = entity.getIsKosalaamRoom();
        this.name = entity.getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.images = entity.getImages();
        this.likedCount = entity.getLikedCount();
        this.openingHours = entity.getOpeningHours();
        this.isParkingLot = entity.getIsParkingLot();
        this.managingType = entity.getManagingType();
        this.praySupplies = new PraySupplies(
                entity.getIsKoran(),
                entity.getIsMat(),
                entity.getIsQibla(),
                entity.getIsWashingRoom()
        );
        this.hostId = entity.getHostId();
        this.detailInfo = entity.getDetailInfo();
        this.isLiked = Boolean.FALSE;
    }

    /**
     * DTO to Entity
     * @return 기도실 Entity
     */
    public Prayerroom toEntity() {
        return Prayerroom.builder()
                .isKosalaamRoom(isKosalaamRoom)
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .images(images)
                .likedCount(likedCount)
                .openingHours(openingHours)
                .isParkingLot(isParkingLot)
                .managingType(managingType)
                .isKoran(praySupplies.getIsKoran())
                .isMat(praySupplies.getIsMat())
                .isQibla(praySupplies.getIsQibla())
                .isWashingRoom(praySupplies.getIsWashingRoom())
                .hostId(hostId)
                .detailInfo(detailInfo)
                .build();
    }
}
