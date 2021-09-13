package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.common.FirebaseUtils;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import com.kosalaam.api.modules.restaurant.domain.*;
import com.kosalaam.api.modules.restaurant.dto.*;
import com.kosalaam.api.modules.restaurant.dto.RestaurantReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantLikeRepository restaurantLikeRepository;

    private final RestaurantReviewRepository restaurantReviewRepository;

    private final KoUserRepository koUserRepository;

    private final FirebaseUtils firebaseUtils;

    @Transactional
    public RestaurantRespDto getRestaurant(Long id, String token) throws Exception {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        RestaurantRespDto restaurantRespDto = new RestaurantRespDto(restaurant);

        if (token != null) {

            // user 체크
            String firebaseUuid = firebaseUtils.checkToken(token);
            KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "'"+token+"' 는 존재하지 않는 사용자입니다."
                    ));

            // 좋아요 체크
            if (restaurantLikeRepository.findByKoUserIdAndRestaurantId(koUser.getId(), id).isPresent()) {
                restaurantRespDto.setIsLiked(Boolean.TRUE);
            }
        }

        return restaurantRespDto;

    }

    @Transactional
    public List<RestaurantRespDto> getRestaurants(int pageNum, int pageSize) throws Exception {

        List<Restaurant> restaurants = restaurantRepository.findAll(
                PageRequest.of(pageNum,pageSize)
        ).getContent();

        return restaurants.stream()
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
            throw new IllegalArgumentException("이미 좋아요로 등록한 식당입니다.");
        }
        RestaurantLike restaurantLike = new RestaurantLike(koUser.getId(), restaurantId);
        restaurantLikeRepository.save(restaurantLike);

    }

    @Transactional
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
        RestaurantLike restaurantLike = restaurantLikeRepository
                .findByKoUserIdAndRestaurantId(koUser.getId(), restaurantId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "좋아요로 등록되지 않은 식당입니다."
                ));

        restaurantLikeRepository.deleteById(restaurantLike.getId());
    }

    @Transactional
    public RestaurantReviewsDto getRestaurantReviews(Long id) throws Exception {

        List<RestaurantReviewDto> restaurantReviewDtos = Optional.ofNullable(
                restaurantReviewRepository.findByRestaurantId(id)
        ).orElseGet(ArrayList::new)
                .stream()
                .map(RestaurantReviewDto::new)
                .collect(Collectors.toList());

        return RestaurantReviewsDto.builder()
                .reviewCnt(restaurantReviewDtos.size())
                .restaurantReviews(restaurantReviewDtos)
                .build();

    }
}
