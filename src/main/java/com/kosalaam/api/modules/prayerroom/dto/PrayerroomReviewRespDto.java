package com.kosalaam.api.modules.prayerroom.dto;

import com.kosalaam.api.modules.prayerroom.domain.PrayerroomReview;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
@ApiModel
public class PrayerroomReviewRespDto {

    @ApiModelProperty(notes = "기도실 리뷰 ID")
    private Long id;

    @ApiModelProperty(notes = "기도실 ID")
    private UUID PrayerroomId;

    @ApiModelProperty(notes = "사용자 ID")
    private Long koUserId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;

    public PrayerroomReviewRespDto(PrayerroomReview entity) {
        this.id = entity.getId();
        this.PrayerroomId = entity.getPrayerroomId();
        this.koUserId = entity.getKoUserId();
        this.comment = entity.getComment();
    }
}
