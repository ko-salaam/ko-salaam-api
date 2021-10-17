package com.kosalaam.api.modules.restaurant.domain;

import com.kosalaam.api.modules.place.domain.Place;
import com.kosalaam.api.modules.restaurant.dto.RestaurantDto;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.TypeDef;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Table(name="restaurant")
@Entity
public class Restaurant extends Place {

    @Column(name="dish_type")
    private String dishType;

    @Enumerated(EnumType.STRING)
    @Column(name="muslim_friendly")
    private MuslimFriendlies muslimFriendly;

    @Builder
    public Restaurant(UUID id, String name, double latitude, double longitude, String address, String phoneNumber, String[] images, int likedCount, String openingHours, String holiday, Boolean isParkingLot, String detailInfo, String dishType, MuslimFriendlies muslimFriendly) {
        super(id, name, latitude, longitude, address, phoneNumber, images, likedCount, openingHours, holiday, isParkingLot, detailInfo);
        this.dishType = dishType;
        this.muslimFriendly = muslimFriendly;
    }

    /**
     * 식당 정보 update
     * @param restaurantDto 식당 DTO
     */
    public void update(RestaurantDto restaurantDto) {
        if (ObjectUtils.isEmpty(restaurantDto))
            throw new IllegalArgumentException("요청 파라미터가 NULL 입니다.");

        if (restaurantDto.getName() != null) {
            this.name = restaurantDto.getName();
        }

        if (restaurantDto.getLatitude() != 0) {
            this.latitude = restaurantDto.getLatitude();
        }

        if (restaurantDto.getLongitude() != 0) {
            this.longitude = restaurantDto.getLongitude();
        }

        if (restaurantDto.getAddress() != null) {
            this.address = restaurantDto.getAddress();
        }

        if (restaurantDto.getPhoneNumber() != null) {
            this.phoneNumber = restaurantDto.getPhoneNumber();
        }

        if (restaurantDto.getImages() != null) {
            this.images = restaurantDto.getImages();
        }

        if (restaurantDto.getDishType() != null) {
            this.dishType = restaurantDto.getDishType();
        }

        if (restaurantDto.getMuslimFriendly() != null) {
            this.muslimFriendly = restaurantDto.getMuslimFriendly();
        }

        if (restaurantDto.getOpeningHours() != null) {
            this.openingHours = restaurantDto.getOpeningHours();
        }

        if (restaurantDto.getIsParkingLot() != null) {
            this.isParkingLot = restaurantDto.getIsParkingLot();
        }

        if (restaurantDto.getDetailInfo() != null) {
            this.detailInfo = restaurantDto.getDetailInfo();
        }

    }
}
