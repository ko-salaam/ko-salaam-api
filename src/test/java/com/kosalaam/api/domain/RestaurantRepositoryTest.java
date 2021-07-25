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
import java.util.Optional;

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
        System.out.println("test");
    }
}
