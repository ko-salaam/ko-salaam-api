package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import com.kosalaam.api.modules.restaurant.domain.RestaurantRepository;
import com.kosalaam.api.modules.restaurant.dto.RestaurantRespDto;
import com.kosalaam.api.modules.restaurant.dto.RestaurantSaveReqDto;
import com.kosalaam.api.modules.restaurant.dto.RestaurantUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantRespDto getRestaurant(Long id) throws Exception {

        Restaurant entity = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));
        return new RestaurantRespDto(entity);

    }

    @Transactional
    public List<RestaurantRespDto> getRestaurants(int pageNum, int pageSize) throws Exception {

        List<Restaurant> entities = restaurantRepository.findAll(
                PageRequest.of(pageNum,pageSize)
        ).getContent();

        return entities.stream()
                .map(RestaurantRespDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public Long saveRestaurant(RestaurantSaveReqDto restaurantSaveReqDto) throws Exception {
        return restaurantRepository.save(restaurantSaveReqDto.toEntity()).getId();
    }

    @Transactional
    public void updateRestaurant(Long id, RestaurantUpdateReqDto restaurantUpdateReqDto) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        restaurant.update(restaurantUpdateReqDto);
    }

    @Transactional
    public void deleteRestaurant(Long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));
        restaurantRepository.delete(restaurant);
    }
}
