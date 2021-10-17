package com.kosalaam.api.modules.accommodation.domain;

import com.kosalaam.api.modules.accommodation.dto.AccommodationDto;
import com.kosalaam.api.modules.place.domain.Place;
import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Table(name="accommodation")
@Entity
public class Accommodation extends Place {

    @Column(name="is_muslim_friendly")
    private Boolean isMuslimFriendly;

    @Column(name="is_koran")
    private Boolean isKoran;

    @Column(name="is_mat")
    private Boolean isMat;

    @Column(name="is_qibla")
    private Boolean isQibla;

    @Column(name="is_washing_room")
    private Boolean isWashingRoom;


    @Builder
    public Accommodation(UUID id, String name, double latitude, double longitude, String address, String phoneNumber, String[] images, int likedCount, String openingHours, String holiday, Boolean isParkingLot, String detailInfo, Boolean isMuslimFriendly, Boolean isKoran, Boolean isMat, Boolean isQibla, Boolean isWashingRoom) {
        super(id, name, latitude, longitude, address, phoneNumber, images, likedCount, openingHours, holiday, isParkingLot, detailInfo);
        this.isMuslimFriendly = isMuslimFriendly;
        this.isKoran = isKoran;
        this.isMat = isMat;
        this.isQibla = isQibla;
        this.isWashingRoom = isWashingRoom;
    }

    /**
     * Accommodation 정보 업데이트
     * @param accommodationDto 숙소 DTO
     */
    public void update(AccommodationDto accommodationDto) {
        if (ObjectUtils.isEmpty(accommodationDto))
            throw new IllegalArgumentException("요청 파라미터가 NULL 입니다.");
        if (accommodationDto.getName() != null) {
            this.name = accommodationDto.getName();
        }
        if (accommodationDto.getLatitude() != 0) {
            this.latitude = accommodationDto.getLatitude();
        }
        if (accommodationDto.getLongitude() != 0) {
            this.longitude = accommodationDto.getLongitude();
        }
        if (accommodationDto.getAddress() != null) {
            this.address = accommodationDto.getAddress();
        }
        if (accommodationDto.getPhoneNumber() != null) {
            this.phoneNumber = accommodationDto.getPhoneNumber();
        }
        if (accommodationDto.getImages() != null) {
            this.images = accommodationDto.getImages();
        }
        if (accommodationDto.getIsMuslimFriendly() != null) {
            this.isMuslimFriendly = accommodationDto.getIsMuslimFriendly();
        }
        if (accommodationDto.getPraySupplies() != null) {
            this.isKoran = accommodationDto.getPraySupplies().getIsKoran();
            this.isMat = accommodationDto.getPraySupplies().getIsMat();
            this.isQibla = accommodationDto.getPraySupplies().getIsQibla();
            this.isWashingRoom = accommodationDto.getPraySupplies().getIsWashingRoom();
        }

        if (accommodationDto.getIsParkingLot() != null) {
            this.isParkingLot = accommodationDto.getIsParkingLot();
        }
        if (accommodationDto.getDetailInfo() != null) {
            this.detailInfo = accommodationDto.getDetailInfo();
        }
        
    }


}
