package com.kosalaam.api.modules.prayerroom.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosalaam.api.modules.place.domain.PraySupplies;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.prayerroom.domain.Prayerroom;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;


@AllArgsConstructor
//@ApiModel
public class PrayerroomSaveDto extends PlaceDto {


//    @ApiModelProperty(notes = "코살람 등록 기도실 여부", position = 2)
    public Boolean isKosalaamRoom;

//    @ApiModelProperty(notes = "가격", position = 14)
    public Integer price;

//    @ApiModelProperty(notes = "이미지 리스트")
//    private List<MultipartFile> imageFiles;

    @ApiModelProperty(notes = "기도 물품 구비 여부")
    private String praySupplies;

    @ApiModelProperty(notes = "기도실 운영 타입", position = 14)
    public String managingType;

    @ApiModelProperty(notes = "호스트 ID", position = 16)
    public Long hostId;

    public Prayerroom toEntity() throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        PraySupplies newPraySupplies = mapper.readValue(praySupplies, PraySupplies.class);

        return Prayerroom.builder()
                .isKosalaamRoom(isKosalaamRoom)
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .likedCount(likedCount)
                .openingHours(openingHours)
                .isParkingLot(isParkingLot)
                .managingType(managingType)
                .isKoran(newPraySupplies.getIsKoran())
                .isMat(newPraySupplies.getIsMat())
                .isQibla(newPraySupplies.getIsQibla())
                .isWashingRoom(newPraySupplies.getIsWashingRoom())
                .hostId(hostId)
                .detailInfo(detailInfo)
                .build();
    }
}
