package com.kosalaam.api.modules.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel
public class PraySupplies {

    @ApiModelProperty(notes = "코란 여부", position = 1)
    private Boolean isKoran;

    @ApiModelProperty(notes = "매트 여부", position = 2)
    private Boolean isMat;

    @ApiModelProperty(notes = "키블라 여부", position = 4)
    private Boolean isQibla;

    @ApiModelProperty(notes = "세족실 여부", position = 5)
    private Boolean isWashingRoom;


}
