package com.kosalaam.api.domain;

import com.kosalaam.api.domain.restaurant.MuslimFriendlies;
import com.kosalaam.api.domain.restaurant.Restaurant;
import com.kosalaam.api.domain.restaurant.RestaurantRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

//    @After
//    public void cleanup() {
//        restaurantRepository.deleteAll();
//    }

    @Test
    public void saveAndGetRestaurant() {
//        System.out.println("test");

        // given
        String name = "이름01";
        Long imagesId = 1L;
//        restaurantRepository.findAll();
//        restaurantRepository.save(Restaurant.builder()
//                .name(name)
//                .latitude(37.134)
//                .longitude(127.234)
//                .address("주소01")
//                .phoneNumber("010-3455-2345")
//                .imagesId(imagesId)
//                .dishType("distType01")
//                .muslimFriendlies(MuslimFriendlies.MUSLIM_FRIENDLY)
//                .openingHours("openHours01")
//                .isParkingLot(true)
//                .build()
//        );

        // when
        List<Restaurant> restaurants = restaurantRepository.findAll();
        System.out.println(restaurants);
        // then
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getName()).isEqualTo(name);
        assertThat(restaurant.getImagesId()).isEqualTo(imagesId);

    }
}
