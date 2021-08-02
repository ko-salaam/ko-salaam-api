package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.modules.restaurant.domain.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
