package com.kosalaam.api.domain.accommodation;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="accommodation")
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @ApiModelProperty(notes = "숙소 ID", position = 1)
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
    private Long imagesId;

    @Column(name="liked_count")
    @ApiModelProperty(notes = "좋아요 수", position = 8)
    private int likedCount;

    @Column(name="opening_hours")
    @ApiModelProperty(notes = "영업 시간", position = 9)
    private String openingHours;

    @Column(name="is_parking_lot")
    @ApiModelProperty(notes = "주차장 여부", position = 10)
    private Boolean isParkingLot;

    @Column(name="is_muslim_friendly")
    @ApiModelProperty(notes = "무슬림 친화 여부", position = 11)
    private Boolean isMuslimFriendly;

    @Column(name="is_prayer_room")
    @ApiModelProperty(notes = "기도실 여부", position = 12)
    private Boolean isPrayerRoom;

    @Column(name="is_koran")
    @ApiModelProperty(notes = "코란 여부", position = 13)
    private Boolean isKoran;

    @Column(name="is_mat")
    @ApiModelProperty(notes = "매트 여부", position = 14)
    private Boolean isMat;

    @Column(name="is_clothes")
    @ApiModelProperty(notes = "기도실 의상 여부", position = 15)
    private Boolean isClothes;

    @Column(name="is_qibla")
    @ApiModelProperty(notes = "키블라 여부", position = 16)
    private Boolean isQibla;

    @Column(name="is_washing_room")
    @ApiModelProperty(notes = "세족실 여부", position = 17)
    private Boolean isWashingRoom;

    @Column(name="detail_info")
    @ApiModelProperty(notes = "비고", position = 18)
    private String detailInfo;

}
