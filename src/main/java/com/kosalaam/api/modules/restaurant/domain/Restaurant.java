package com.kosalaam.api.modules.restaurant.domain;

import com.kosalaam.api.modules.restaurant.dto.RestaurantUpdateDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name="restaurant")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @ApiModelProperty(notes = "식당 ID", position = 1)
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "이름", position = 2)
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(notes = "위도", position = 3)
    private double latitude;

    @Column(nullable = false)
    @ApiModelProperty(notes = "경도", position = 4)
    private double longitude;

    @Column(length = 500, nullable = false)
    @ApiModelProperty(notes = "상세 주소", position = 5)
    private String address;

    @Column(name="phone_number")
    @ApiModelProperty(notes = "전화번호", position = 6)
    private String phoneNumber;

    @Column(name="images_id")
    @ApiModelProperty(notes = "사진 ID", position = 7)
    private String imagesId;

    @Column(name="dish_type")
    @ApiModelProperty(notes = "요리 분류", position = 8)
    private String dishType;

    @Enumerated(EnumType.STRING)
    @Column(name="muslim_friendly")
    @ApiModelProperty(notes = "무슬림 친화", position = 8)
    private MuslimFriendlies muslimFriendly;

    @Column(name="liked_count")
    @ApiModelProperty(notes = "좋아요 수", position = 8)
    private int likedCount;

    @Column(name="opening_hours")
    @ApiModelProperty(notes = "영업 시간", position = 10)
    private String openingHours;

    @Column
    @ApiModelProperty(notes = "휴일", position = 11)
    private String holiday;

    @Column(name="is_parking_lot")
    @ApiModelProperty(notes = "주차장 여부", position = 12)
    private Boolean isParkingLot;

    @Column(name="detail_info")
    @ApiModelProperty(notes = "비고", position = 13)
    private String detailInfo;

    public void update(RestaurantUpdateDto restaurantUpdateReqDto) {
        if (ObjectUtils.isEmpty(restaurantUpdateReqDto))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");

        if (restaurantUpdateReqDto.getName() != null) {
            this.name = restaurantUpdateReqDto.getName();
        }

        if (restaurantUpdateReqDto.getLatitude() != 0) {
            this.latitude = restaurantUpdateReqDto.getLatitude();
        }

        if (restaurantUpdateReqDto.getLongitude() != 0) {
            this.longitude = restaurantUpdateReqDto.getLongitude();
        }

        if (restaurantUpdateReqDto.getAddress() != null) {
            this.address = restaurantUpdateReqDto.getAddress();
        }

        if (restaurantUpdateReqDto.getPhoneNumber() != null) {
            this.phoneNumber = restaurantUpdateReqDto.getPhoneNumber();
        }

        if (restaurantUpdateReqDto.getImagesId() != null) {
            this.imagesId = restaurantUpdateReqDto.getImagesId();
        }

        if (restaurantUpdateReqDto.getDishType() != null) {
            this.dishType = restaurantUpdateReqDto.getDishType();
        }

        if (restaurantUpdateReqDto.getMuslimFriendly() != null) {
            this.muslimFriendly = restaurantUpdateReqDto.getMuslimFriendly();
        }

        if (restaurantUpdateReqDto.getOpeningHours() != null) {
            this.openingHours = restaurantUpdateReqDto.getOpeningHours();
        }

        if (restaurantUpdateReqDto.getHoliday() != null) {
            this.holiday = restaurantUpdateReqDto.getHoliday();
        }

        if (restaurantUpdateReqDto.getIsParkingLot() != null) {
            this.isParkingLot = restaurantUpdateReqDto.getIsParkingLot();
        }

        if (restaurantUpdateReqDto.getDetailInfo() != null) {
            this.detailInfo = restaurantUpdateReqDto.getDetailInfo();
        }

    }
}
