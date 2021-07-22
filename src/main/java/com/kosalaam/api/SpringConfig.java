package com.kosalaam.api;

import com.kosalaam.api.domain.restaurant.RestaurantRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final RestaurantRepository restaurantRepository;

    public SpringConfig(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
}
