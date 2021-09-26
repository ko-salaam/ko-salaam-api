package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.common.UnauthorizedException;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
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

import static com.kosalaam.api.common.ExceptionFunction.wrapper;


@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantLikeRepository restaurantLikeRepository;

    private final RestaurantReviewRepository restaurantReviewRepository;

    private final KoUserRepository koUserRepository;

    /**
     * 식당 조회
     * @param id 식당 ID
     * @param firebaseUuid Firebase UUID
     * @return 식당 DTO
     * @throws IllegalArgumentException 존재하지 않는 식당 ID or Auth 에러
     */
    @Transactional
    public RestaurantDto getRestaurant(Long id, String firebaseUuid) throws IllegalArgumentException {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        return writeDto(restaurant, firebaseUuid);

    }

    /**
     * 식당 리스트 조회
     * @param latitude 위도
     * @param longitude 경도
     * @param distance 반경 Nkm
     * @param keyword 검색 키워드
     * @param pageNum 페이지 번호
     * @param pageSize 페이지 사이즈
     * @param firebaseUuid firebase uuid
     * @return DTO 리스트
     */
    @Transactional
    public List<RestaurantDto> getRestaurants(double latitude, double longitude, int distance, String keyword, int pageNum, int pageSize, String firebaseUuid) {

        if (keyword == null) { keyword = ""; }
        List<Restaurant> restaurants = restaurantRepository.findByLocation(
                latitude,
                longitude,
                distance,
                keyword,
                PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "liked_count")
        ).getContent();


        return restaurants.stream()
                .map(wrapper(r -> writeDto(r, firebaseUuid)))
                .collect(Collectors.toList());

    }

    /**
     * 식당 저장
     * @param restaurantDto 식당 DTO
     * @return 식당 DTO
     */
    @Transactional
    public RestaurantDto saveRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.save(restaurantDto.toEntity());
        return writeDto(restaurant, "");
    }

    /**
     * 식당 정보 수정
     * @param id 식당 ID
     * @param restaurantDto 식당 DTO
     * @return 식당 DTO
     * @throws UnauthorizedException 존재하지 않는 식당 ID
     */
    @Transactional
    public RestaurantDto updateRestaurant(Long id, RestaurantDto restaurantDto) throws UnauthorizedException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        // update
        restaurant.update(restaurantDto);
        return writeDto(restaurant, "");
    }

    /**
     * 식당 삭제
     * @param id 식당 ID
     * @throws IllegalArgumentException 존재하지 않는 식당 ID
     */
    @Transactional
    public void deleteRestaurant(Long id) throws IllegalArgumentException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 식당 ID 입니다."
                ));
        restaurantRepository.delete(restaurant);
    }


    /**
     * 좋아요 등록
     * @param restaurantId 식당 ID
     * @param firebaseUuid Firebase UUID
     */
    @Transactional
    public void setLikeRestaurant(Long restaurantId, String firebaseUuid) {

        // user
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
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

        // 좋아요 수 반영
        restaurant.setLikedCount(restaurant.getLikedCount() + 1);

    }

    /**
     * 좋아요 취소
     * @param restaurantId 식당 ID
     * @param firebaseUuid Firebase UUID
     * @throws RuntimeException 존재하지 않는 식당 ID or Auth 에러
     */
    @Transactional
    public void deleteLikeRestaurant(Long restaurantId, String firebaseUuid) throws RuntimeException {

        // user
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));

        // restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+restaurantId+"' 는 존재하지 않는 식당 ID 입니다."
                ));

        // cancel like
        RestaurantLike restaurantLike = restaurantLikeRepository
                .findByKoUserIdAndRestaurantId(koUser.getId(), restaurantId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "좋아요로 등록되지 않은 식당입니다."
                ));

        restaurantLikeRepository.deleteById(restaurantLike.getId());

        // 좋아요 수 반영
        restaurant.setLikedCount(restaurant.getLikedCount() - 1);
    }

    /**
     * 식당 리뷰 리스트 조회
     * @param id 식당 ID
     * @return RestaurantReviewsDto 식당 리뷰 DTO
     */
    @Transactional
    public RestaurantReviewsRespDto getRestaurantReviews(Long id) {

        List<RestaurantReviewRespDto> restaurantReviewRespDtos = Optional.ofNullable(
                restaurantReviewRepository.findByRestaurantId(id)
        ).orElseGet(ArrayList::new)
                .stream()
                .map(RestaurantReviewRespDto::new)
                .collect(Collectors.toList());

        return RestaurantReviewsRespDto.builder()
                .reviewCnt(restaurantReviewRespDtos.size())
                .reviews(restaurantReviewRespDtos)
                .build();

    }

    /**
     * 식당 리뷰 등록
     * @param restaurantReviewSaveDto 리뷰 등록용 DTO
     * @return 식당 리뷰 DTO
     * @throws UnauthorizedException Auth 에러
     */
    @Transactional
    public Long saveRestaurantReview(RestaurantReviewSaveDto restaurantReviewSaveDto, String firebaseUuid) throws UnauthorizedException {

        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));
        RestaurantReview restaurantReview = restaurantReviewSaveDto.toEntity(koUser.getId());
        return restaurantReviewRepository.save(restaurantReview).getId();

    }

    /**
     * 식당 리뷰 삭제
     * @param id 식당 리뷰 ID
     * @return 식당 리뷰 ID
     * @throws RuntimeException 존재하지 않는 식당 리뷰 ID
     */
    @Transactional
    public Long deleteRestaurantReview(Long id, String firebaseUuid) throws RuntimeException {

        RestaurantReview restaurantReview = restaurantReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 식당 리뷰 ID 입니다."
                ));

        // 삭제 권한 확인
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));
        if (restaurantReview.getKoUserId().equals(koUser.getId())) {
            throw new UnauthorizedException("삭제 권한이 없는 식당 리뷰입니다.");
        }

        // 삭제
        restaurantReviewRepository.delete(restaurantReview);

        return id;
    }

    /**
     * Restaurant 엔티티를 DTO 로 변환하고 좋아요 상태를 반영
     * @param restaurant 식당 Entity
     * @param firebaseUuid Firebase UUID
     * @return 식당 DTO
     * @throws UnauthorizedException Auth 에러
     */
    private RestaurantDto writeDto(Restaurant restaurant, String firebaseUuid) throws UnauthorizedException {

        RestaurantDto restaurantDto = new RestaurantDto(restaurant);

        // 좋아요 체크
        if (!firebaseUuid.equals("")) {
            KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                    .orElseThrow(() -> new UnauthorizedException("존재하지 않는 사용자입니다."));

            if (restaurantLikeRepository.findByKoUserIdAndRestaurantId(koUser.getId(), restaurant.getId()).isPresent()) {
                restaurantDto.setIsLiked(Boolean.TRUE);
            }
        }

        return restaurantDto;
    }

}
