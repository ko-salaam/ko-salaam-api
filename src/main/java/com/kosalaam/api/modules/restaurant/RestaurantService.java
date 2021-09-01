package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.config.common.FirebaseUtils;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import com.kosalaam.api.modules.restaurant.domain.RestaurantLike;
import com.kosalaam.api.modules.restaurant.domain.RestaurantLikeRepository;
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

    private final RestaurantLikeRepository restaurantLikeRepository;

    private final KoUserRepository koUserRepository;

    private final FirebaseUtils firebaseUtils;

    @Transactional
    public RestaurantRespDto getRestaurant(Long id, String token) throws Exception {

        // user 체크
        String firebaseUuid = firebaseUtils.checkToken(token);
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+token+"' 는 존재하지 않는 사용자입니다."
                ));

        Restaurant entity = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));


        // 좋아요 체크
        RestaurantRespDto restaurantRespDto = new RestaurantRespDto(entity);
        if (restaurantLikeRepository.findByKoUserIdAndRestaurantId(koUser.getId(), id).isPresent()) {
            restaurantRespDto.setIsLiked(Boolean.FALSE);
        }

        return restaurantRespDto;

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


    @Transactional
    public void setLikeRestaurant(Long restaurantId, String token) throws Exception {

        // user
        String firebaseUuid = firebaseUtils.checkToken(token);
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+token+"' 는 존재하지 않는 사용자입니다."
                ));

        // restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+restaurantId+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        // like
        if (restaurantLikeRepository.findByKoUserIdAndRestaurantId(
                koUser.getId(), restaurantId
        ).isPresent()) {
            new IllegalArgumentException("이미 좋아요로 등록한 식당입니다.");
        }
        RestaurantLike restaurantLike = new RestaurantLike(koUser.getId(), restaurantId);
        restaurantLikeRepository.save(restaurantLike);

    }

    public void deleteLikeRestaurant(Long restaurantId, String token) throws Exception {

        // user
        String firebaseUuid = firebaseUtils.checkToken(token);
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+token+"' 는 존재하지 않는 사용자입니다."
                ));

        // restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+restaurantId+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        // like
        if (!restaurantLikeRepository.findByKoUserIdAndRestaurantId(
                koUser.getId(), restaurantId
        ).isPresent()) {
            new IllegalArgumentException("좋아요로 등록되지 않은 식당입니다.");
        }
        RestaurantLike restaurantLike = new RestaurantLike(koUser.getId(), restaurantId);
        restaurantLikeRepository.save(restaurantLike);
    }
}
