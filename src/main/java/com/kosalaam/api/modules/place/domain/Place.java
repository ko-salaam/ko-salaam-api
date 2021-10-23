package com.kosalaam.api.modules.place.domain;


import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "string-array",typeClass = StringArrayType.class)
@MappedSuperclass
public abstract class Place {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public UUID id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public double latitude;

    @Column(nullable = false)
    public double longitude;

    @Column(length = 500, nullable = false)
    public String address;

    @Column(name="phone_number")
    public String phoneNumber;

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    public String[] images;

    @Column(name="liked_count")
    public int likedCount;

    @Column(name="opening_hours")
    public String openingHours;

    @Column
    public String holiday;

    @Column(name="is_parking_lot")
    public Boolean isParkingLot;

    @Column(name="detail_info")
    public String detailInfo;

//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private Date createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

}
