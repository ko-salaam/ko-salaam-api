package com.kosalaam.api.modules.accommodation.domain;

import com.kosalaam.api.modules.accommodation.dto.AccommodationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="accommodation")
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto generated
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(length = 500, nullable = false)
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="images_id")
    private Long imagesId;

    @Column(name="liked_count")
    private int likedCount;

    @Column(name="opening_hours")
    private String openingHours;

    @Column(name="is_parking_lot")
    private Boolean isParkingLot;

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

    @Column(name="detail_info")
    private String detailInfo;

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
        if (accommodationDto.getImagesId() != null) {
            this.imagesId = accommodationDto.getImagesId();
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
        if (accommodationDto.getOpeningHours() != null) {
            this.openingHours = accommodationDto.getOpeningHours();
        }
        if (accommodationDto.getIsParkingLot() != null) {
            this.isParkingLot = accommodationDto.getIsParkingLot();
        }
        if (accommodationDto.getDetailInfo() != null) {
            this.detailInfo = accommodationDto.getDetailInfo();
        }
        
    }


}
