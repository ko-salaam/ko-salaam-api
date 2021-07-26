package com.kosalaam.api.modules.prayerroom.dto;

import io.swagger.annotations.ApiModelProperty;

public class PrayerroomReviewRespDto {
    @ApiModelProperty(notes = "기도실 리뷰 ID")
    private Long id;

    @ApiModelProperty(notes = "기도실 ID")
    private Long PrayerroomId;

    @ApiModelProperty(notes = "사용자 ID")
    private Long KoUserId;

    @ApiModelProperty(notes = "리뷰 내용")
    private String comment;
}
