package com.kosalaam.api.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestaurantRespDto {

    private final Long restaurantId;
    private final String name;

}
