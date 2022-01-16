package com.kosalaam.api.modules.prayerroom.dto;

import com.kosalaam.api.modules.prayerroom.domain.Prayerroom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.UUID;


@Getter
@ApiModel
public class PrayerroomSaveDto {

    @ApiModelProperty(notes = "전체 UUID", position = 1)
    public UUID id;

    @ApiModelProperty(notes = "코살람 등록 기도실 여부", position = 2)
    public Boolean isKosalaamRoom;

//    @ApiModelProperty(notes = "가격", position = 2)
//    public Integer price;
//
////    @ApiModelProperty(notes = "기도 물품 구비 여부")
////    private String praySupplies;
//
//    @ApiModelProperty(notes = "기도실 운영 타입", position = 3)
//    public String managingType;
//
//    @ApiModelProperty(notes = "호스트 ID", position = 4)
//    public Long hostId;

    public Prayerroom toEntity() throws Exception {

//        final ObjectMapper mapper = new ObjectMapper();
//        PraySupplies newPraySupplies = mapper.readValue(praySupplies, PraySupplies.class);

        return new Prayerroom();
//        return Prayerroom.builder()
//                .isKosalaamRoom(isKosalaamRoom)
//                .name(name)
//                .latitude(latitude)
//                .longitude(longitude)
//                .address(address)
//                .phoneNumber(phoneNumber)
//                .likedCount(likedCount)
//                .openingHours(openingHours)
//                .isParkingLot(isParkingLot)
//                .managingType(managingType)
////                .isKoran(newPraySupplies.getIsKoran())
////                .isMat(newPraySupplies.getIsMat())
////                .isQibla(newPraySupplies.getIsQibla())
////                .isWashingRoom(newPraySupplies.getIsWashingRoom())
//                .hostId(hostId)
//                .detailInfo(detailInfo)
//                .build();
    }
}
