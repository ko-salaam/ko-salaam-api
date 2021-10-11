package com.kosalaam.api.modules.restaurant.domain;

import com.kosalaam.api.modules.restaurant.dto.RestaurantDto;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@TypeDef(name = "string-array",typeClass = StringArrayType.class)
@Table(name="restaurant")
@Entity
public class Restaurant {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    private String[] images;

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
