package com.kosalaam.api.modules.prayerroom.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="prayerroom")
@Entity
public class Prayerroom {

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

    @Column
    private String holiday;

    @Column(name="is_parking_lot")
    private Boolean isParkingLot;

    @Column(name="is_koran")
    private Boolean isKoran;

    @Column(name="is_mat")
    private Boolean isMat;

    @Column(name="is_qibla")
    private Boolean isQibla;

    @Column(name="is_washing_room")
    private Boolean isWashingRoom;

    @Column(name = "is_kosalaam_room", nullable = false)
    private Boolean isKosalaamRoom;

    @Column(name = "host_id")
    private Long hostId;

    @Column
    private int price;

    @Column(name="detail_info")
    private String detailInfo;
}
