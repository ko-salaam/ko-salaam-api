package com.kosalaam.api.domain.restaurant;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Table(name="restaurant")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @ApiModelProperty(notes = "식당 ID", position = 1)
    private Long restaurantId;

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

    @Column
    @ApiModelProperty(notes = "전화번호", position = 6)
    private String phoneNumber;

    @Column
    @ApiModelProperty(notes = "사진 ID", position = 7)
    private Long imagesId;

    @Column
    @ApiModelProperty(notes = "요리 분류", position = 8)
    private MuslimFriendlies DishType;

    @Column
    @ApiModelProperty(notes = "좋아요 수", position = 8)
    private int likedCount;

    @Column
    @ApiModelProperty(notes = "평점", position = 9)
    private float rating;

    @Column
    @ApiModelProperty(notes = "영업 시간", position = 10)
    private String openingHours;

    @Column
    @ApiModelProperty(notes = "주차장 여부", position = 11)
    private Boolean isParkingLot;

}
