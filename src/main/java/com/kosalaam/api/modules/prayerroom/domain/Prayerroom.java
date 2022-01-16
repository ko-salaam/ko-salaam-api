package com.kosalaam.api.modules.prayerroom.domain;

import com.kosalaam.api.modules.place.domain.Place;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomDto;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Table(name="prayerroom")
@Entity
public class Prayerroom extends Place {

    @Column(name="is_kosalaam_room", nullable = false)
    private Boolean isKosalaamRoom;

    @Column
    private Integer price;

    @Column(name="managing_type")
    private String managingType;

    @Column(name="is_koran")
    private Boolean isKoran;

    @Column(name="is_mat")
    private Boolean isMat;

    @Column(name="is_qibla")
    private Boolean isQibla;

    @Column(name="is_washing_room")
    private Boolean isWashingRoom;

    @Column(name = "host_id")
    private Long hostId;

    @Builder
    public Prayerroom(UUID id, String name, double latitude, double longitude, String address, String phoneNumber, String[] images, int likedCount, String openingHours, String holiday, Boolean isParkingLot, String detailInfo, Boolean isKosalaamRoom, int price, String managingType , Boolean isKoran, Boolean isMat, Boolean isQibla, Boolean isWashingRoom, Long hostId) {
        super(id, name, latitude, longitude, address, phoneNumber, images, likedCount, openingHours, holiday, isParkingLot, detailInfo);
        this.isKosalaamRoom = isKosalaamRoom;
        this.price = price;
        this.managingType = managingType;
        this.isKoran = isKoran;
        this.isMat = isMat;
        this.isQibla = isQibla;
        this.isWashingRoom = isWashingRoom;
        this.hostId = hostId;
    }


    /**
     * 기도실 정보 update
     * @param prayerroomDto 기도실 DTO
     */
    public void update(PrayerroomDto prayerroomDto) {
        if (ObjectUtils.isEmpty(prayerroomDto))
            throw new IllegalArgumentException("요청 파라미터가 NULL 입니다.");

        if (prayerroomDto.getIsKosalaamRoom() != null) {
            this.isKosalaamRoom = prayerroomDto.getIsKosalaamRoom();
        }

        if (prayerroomDto.getName() != null) {
            this.name = prayerroomDto.getName();
        }

        if (prayerroomDto.getLatitude() != 0) {
            this.latitude = prayerroomDto.getLatitude();
        }

        if (prayerroomDto.getLongitude() != 0) {
            this.longitude = prayerroomDto.getLongitude();
        }

        if (prayerroomDto.getAddress() != null) {
            this.address = prayerroomDto.getAddress();
        }

        if (prayerroomDto.getPhoneNumber() != null) {
            this.phoneNumber = prayerroomDto.getPhoneNumber();
        }

        if (prayerroomDto.getImages() != null) {
            this.images = prayerroomDto.getImages();
        }

        if (prayerroomDto.getOpeningHours() != null) {
            this.openingHours = prayerroomDto.getOpeningHours();
        }

        if (prayerroomDto.getIsParkingLot() != null) {
            this.isParkingLot = prayerroomDto.getIsParkingLot();
        }

        if (prayerroomDto.getManagingType() != null) {
            this.isParkingLot = prayerroomDto.getIsParkingLot();
        }

        if (prayerroomDto.getPraySupplies() != null) {
            this.isKoran = prayerroomDto.getPraySupplies().getIsKoran();
            this.isMat = prayerroomDto.getPraySupplies().getIsMat();
            this.isQibla = prayerroomDto.getPraySupplies().getIsQibla();
            this.isWashingRoom = prayerroomDto.getPraySupplies().getIsWashingRoom();
        }

        if (prayerroomDto.getHostId() != null) {
            this.isParkingLot = prayerroomDto.getIsParkingLot();
        }

        if (prayerroomDto.getDetailInfo() != null) {
            this.detailInfo = prayerroomDto.getDetailInfo();
        }

    }
}
