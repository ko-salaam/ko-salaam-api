package com.kosalaam.api.modules.common.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlaceType {

    RESTAURANT("RESTAURANT"),
    ACCOMMODATION("ACCOMMODATION"),
    PRAYER_ROOM("PRAYER_ROOM");

    private String type;

}
