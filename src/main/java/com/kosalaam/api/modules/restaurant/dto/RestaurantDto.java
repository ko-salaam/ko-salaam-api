package com.kosalaam.api.modules.restaurant.dto;

import com.kosalaam.api.common.UnauthorizedException;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import com.kosalaam.api.modules.restaurant.domain.RestaurantLikeRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel
public class RestaurantDto {


    @ApiModelProperty(notes = "식당 ID", position = 1)
    private Long id;

    @ApiModelProperty(notes = "전체 UUID", position = 2)
    private UUID uuid;

    @ApiModelProperty(notes = "이름", position = 3)
    private String name;

    @ApiModelProperty(notes = "위도", position = 4)
    private double latitude;

    @ApiModelProperty(notes = "경도", position = 5)
    private double longitude;

    @ApiModelProperty(notes = "상세 주소", position = 6)
    private String address;

    @ApiModelProperty(notes = "전화번호", position = 7)
    private String phoneNumber;

    @ApiModelProperty(notes = "사진 ID", position = 8)
    private String imagesId;

    @ApiModelProperty(notes = "요리 분류", position = 9)
    private String dishType;

    @ApiModelProperty(notes = "무슬림 친화", position = 10)
    private MuslimFriendlies muslimFriendly;

    @ApiModelProperty(notes = "좋아요 수", position = 11)
    private int likedCount;

    @ApiModelProperty(notes = "영업 시간", position = 12)
    private String openingHours;

    @ApiModelProperty(notes = "주차장 여부", position = 13)
    private Boolean isParkingLot;

    @ApiModelProperty(notes = "비고", position = 14)
    private String detailInfo;

    @ApiModelProperty(notes = "좋아요 여부", position = 15)
    private Boolean isLiked;



    /**
     * Entity To DTO
     * @param entity 식당 Entity
     */
    public RestaurantDto(Restaurant entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.imagesId = entity.getImagesId();
        this.dishType = entity.getDishType();
        this.muslimFriendly = entity.getMuslimFriendly();
        if (entity.getMuslimFriendly() == null) {
            this.muslimFriendly = MuslimFriendlies.NONE;
        }
        this.likedCount = entity.getLikedCount();
        this.openingHours = entity.getOpeningHours();
        this.isParkingLot = entity.getIsParkingLot();
        this.detailInfo = entity.getDetailInfo();
        this.isLiked = Boolean.FALSE;
    }

    /**
     * DTO to Entity
     * @return 식당 Entity
     */
    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .imagesId(imagesId)
                .dishType(dishType)
                .muslimFriendly(muslimFriendly)
                .likedCount(likedCount)
                .openingHours(openingHours)
                .isParkingLot(isParkingLot)
                .detailInfo(detailInfo)
                .build();
    }
}
