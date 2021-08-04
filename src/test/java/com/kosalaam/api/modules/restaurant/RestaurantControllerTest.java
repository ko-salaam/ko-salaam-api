package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import com.kosalaam.api.modules.restaurant.domain.RestaurantRepository;
import com.kosalaam.api.modules.restaurant.dto.RestaurantSaveReqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 실행자 지정. springboot test와 junit 사이의 연결자
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void saveRestaurant() throws Exception {
        // given
        String name = "test name";
        double latitude = 127.30113;
        double longitude = 37.49523;
        String address = "address";
        String phoneNumber = "phone";
        String imagesId = "images id";
        String dishType = "dishType";
        MuslimFriendlies muslimFriendly = MuslimFriendlies.HALAL_CERTIFIED;
        String openingHours = "openingHours";


        RestaurantSaveReqDto reqDto = RestaurantSaveReqDto.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .imagesId(imagesId)
                .dishType(dishType)
                .muslimFriendly(muslimFriendly)
                .likedCount(0)
                .openingHours(openingHours)
                .isParkingLot(Boolean.FALSE)
                .detailInfo("test")
                .build();
        String url = "http://localhost:" + port  + "/api/restaurant/info";

        System.out.println("1");
        System.out.println(url);


//        // when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, reqDto, Long.class);
//        System.out.println("2");
//
//        // then
//        assertThat(responseEntity.getStatusCode())
//                .isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody())
//                .isGreaterThan(0L);
//        System.out.println("llllllll");
//
//        List<Restaurant> all = restaurantRepository.findAll();
//        assertThat(all.get(0).getName()).isEqualTo(name);
//        assertThat(all.get(0).getLatitude()).isEqualTo(latitude);
//        assertThat(all.get(0).getMuslimFriendly()).isEqualTo(muslimFriendly);

    }

}
