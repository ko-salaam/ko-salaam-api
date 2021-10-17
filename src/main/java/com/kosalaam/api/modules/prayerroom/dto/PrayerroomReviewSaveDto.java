package com.kosalaam.api.modules.prayerroom.dto;

import com.kosalaam.api.modules.prayerroom.domain.PrayerroomReview;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.UUID;

@Getter // swagger ui에서 필요
public class PrayerroomReviewSaveDto {

    @ApiModelProperty(notes = "기도실 ID")
    private UUID prayerroomId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;

    /**
     * 기도실 리뷰 DTO 를 Entity 로 convert
     * @return PrayerroomReview 객체
     */
    public PrayerroomReview toEntity(Long koUserId) {
        return PrayerroomReview.builder()
                .prayerroomId(prayerroomId)
                .koUserId(koUserId)
                .comment(comment)
                .build();
    }

}
