package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.common.FirebaseUtils;
import com.kosalaam.api.common.UnauthorizedException;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.restaurant.domain.*;
import com.kosalaam.api.modules.restaurant.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private final FirebaseUtils firebaseUtils;

    @Transactional
    public RestaurantRespDto getRestaurant(Long id, String token) throws Exception {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        RestaurantRespDto restaurantRespDto = new RestaurantRespDto(restaurant);

        if (token != null) {
            // 좋아요 체크
            KoUser koUser = firebaseUtils.getKoUser(token);
            if (restaurantLikeRepository.findByKoUserIdAndRestaurantId(koUser.getId(), id).isPresent()) {
                restaurantRespDto.setIsLiked(Boolean.TRUE);
            }
        }

        return restaurantRespDto;

    }

    @Transactional
    public List<RestaurantRespDto> getRestaurants(double latitude, double longitude, int distance, String keyword, int pageNum, int pageSize) throws Exception {

        List<Restaurant> restaurants = restaurantRepository.findByLocation(
                latitude,
                longitude,
                distance,
                keyword,
                PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "liked_count")
        ).getContent();

        return restaurants.stream()
                .map(RestaurantRespDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public Long saveRestaurant(RestaurantSaveDto restaurantSaveDto) throws Exception {
        return restaurantRepository.save(restaurantSaveDto.toEntity()).getId();
    }

    @Transactional
    public void updateRestaurant(Long id, RestaurantUpdateDto restaurantUpdateReqDto) throws Exception {
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
        KoUser koUser = firebaseUtils.getKoUser(token);

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
        KoUser koUser = firebaseUtils.getKoUser(token);

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

    /**
     * 식당 리뷰 리스트 조회
     * @param id 식당 ID
     * @return RestaurantReviewsDto 식당 리뷰 DTO
     * @throws Exception
     */
    @Transactional
    public RestaurantReviewsRespDto getRestaurantReviews(Long id) throws Exception {

        List<RestaurantReviewRespDto> restaurantReviewRespDtos = Optional.ofNullable(
                restaurantReviewRepository.findByRestaurantId(id)
        ).orElseGet(ArrayList::new)
                .stream()
                .map(RestaurantReviewRespDto::new)
                .collect(Collectors.toList());

        return RestaurantReviewsRespDto.builder()
                .reviewCnt(restaurantReviewRespDtos.size())
                .restaurantReviews(restaurantReviewRespDtos)
                .build();

    }

    /**
     * 식당 리뷰 등록
     * @param restaurantReviewSaveDto
     * @return 식당 리뷰 ID
     * @throws Exception
     */
    @Transactional
    public Long saveRestaurantReview(RestaurantReviewSaveDto restaurantReviewSaveDto, String token) throws Exception {

        KoUser koUser = firebaseUtils.getKoUser(token);
        RestaurantReview restaurantReview = restaurantReviewSaveDto.toEntity(koUser.getId());
        return restaurantReviewRepository.save(restaurantReview).getId();

    }

    /**
     * 식당 리뷰 삭제
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional
    public Long deleteRestaurantReview(Long id, String token) throws Exception {

        RestaurantReview restaurantReview = restaurantReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 식당 리뷰 ID 입니다."
                ));

        // 삭제 권한 확인
        KoUser koUser = firebaseUtils.getKoUser(token);
        if (restaurantReview.getKoUserId() != koUser.getId()) {
            throw new UnauthorizedException("삭제 권한이 없는 식당 리뷰입니다.");
        }

        // 삭제
        restaurantReviewRepository.delete(restaurantReview);

        return restaurantReview.getId();
    }

}
