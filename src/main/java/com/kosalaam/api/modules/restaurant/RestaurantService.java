package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import com.kosalaam.api.modules.restaurant.domain.RestaurantRepository;
import com.kosalaam.api.modules.restaurant.dto.RestaurantRespDto;
import com.kosalaam.api.modules.restaurant.dto.RestaurantSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantRespDto findById(Long id) {

        Restaurant entity = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));
        return new RestaurantRespDto(entity);

    }

    @Transactional
    public Long save(RestaurantSaveReqDto restaurantSaveReqDto) {
        System.out.println(restaurantSaveReqDto);
        return restaurantRepository.save(restaurantSaveReqDto.toEntity()).getId();
    }
}
