package com.kosalaam.api.web.dto;

import com.kosalaam.api.web.restaurant.dto.RestaurantRespDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantRespDtoTest {

    @Test
    public void testLombok() {

        Long id = 1L;
        String name = "카메";

        RestaurantRespDto dto = new RestaurantRespDto(id, name);

        assertThat(dto.getRestaurantId()).isEqualTo(id);
        assertThat(dto.getName()).isEqualTo(name);
    }
}
