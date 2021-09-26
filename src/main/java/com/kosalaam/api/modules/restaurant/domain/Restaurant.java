package com.kosalaam.api.modules.restaurant.domain;

import com.kosalaam.api.modules.restaurant.dto.RestaurantDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.UUID;

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
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

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
    private String imagesId;

    @Column(name="dish_type")
    private String dishType;

    @Enumerated(EnumType.STRING)
    @Column(name="muslim_friendly")
    private MuslimFriendlies muslimFriendly;

    @Column(name="liked_count")
    private int likedCount;

    @Column(name="opening_hours")
    private String openingHours;

    @Column
    private String holiday;

    @Column(name="is_parking_lot")
    private Boolean isParkingLot;

    @Column(name="detail_info")
    private String detailInfo;

    /**
     * 식당 정보 update
     * @param restaurantDto
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

        if (restaurantDto.getImagesId() != null) {
            this.imagesId = restaurantDto.getImagesId();
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
