package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.modules.restaurant.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Api(tags = "Restaurant")
@RequestMapping("/api/restaurant")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @ApiOperation(value = "식당 리스트 조회", notes = "반경 Nkm 이내의 식당 리스트를 조회")
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurants(
            @ApiParam(value="현재 위치 위도값", defaultValue = "37.498095") @RequestParam(defaultValue = "37.498095") double latitude,
            @ApiParam(value="현재 위치 경도값", defaultValue = "127.027610") @RequestParam(defaultValue = "127.027610") double longitude,
            @ApiParam(value="반경 N km", defaultValue = "5") @RequestParam(defaultValue = "5") int distance,
            @ApiParam(value="검색 키워드") @RequestParam(required = false) String keyword,
            @ApiParam(value="페이지 번호", defaultValue = "0") @RequestParam(defaultValue = "0") int pageNum,
            @ApiParam(value="페이지 사이즈", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
            ) throws Exception {
        return new ResponseEntity<>(restaurantService.getRestaurants(latitude, longitude, distance, keyword, pageNum, pageSize, firebaseUuid),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 정보 조회", notes = "식당 ID로 상세 정보 조회")
    @GetMapping("{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(
            @ApiParam(value="식당 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.getRestaurant(id, firebaseUuid),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 등록", notes = "식당 등록")
    @PostMapping
    public ResponseEntity<RestaurantDto> saveRestaurant (
            @RequestBody RestaurantDto restaurantDto) throws Exception {
        return new ResponseEntity(restaurantService.saveRestaurant(restaurantDto), HttpStatus.OK);
    }

    @ApiOperation(value = "식당 정보 수정", notes = "식당 ID로 상세 정보 수정")
    @PutMapping("{id}")
    public ResponseEntity updateRestaurant(
            @ApiParam(value="식당 ID") @PathVariable UUID id,
            @ApiParam(value="수정할 정보") @RequestBody RestaurantDto restaurantDto
    ) throws Exception {
        return new ResponseEntity(restaurantService.updateRestaurant(id, restaurantDto), HttpStatus.OK);
    }

    @ApiOperation(value = "식당 삭제", notes = "식당 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity deleteRestaurant (
            @ApiParam(value="식당 ID") @PathVariable UUID id) throws Exception {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 좋아요 등록", notes = "식당에 좋아요 등록")
    @ResponseBody
    @PostMapping("like/{id}")
    public ResponseEntity setRestaurantLike(
            @ApiParam(value="식당 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        restaurantService.setLikeRestaurant(id, firebaseUuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 좋아요 취소", notes = "식당 좋아요 취소")
    @DeleteMapping("like/{id}")
    public ResponseEntity deleteRestaurantLike(
            @ApiParam(value="식당 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        restaurantService.deleteLikeRestaurant(id, firebaseUuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 리뷰 조회", notes = "식당 리뷰 조회")
    @GetMapping("review/{id}")
    public ResponseEntity<RestaurantReviewsRespDto> setRestaurantReview(
            @ApiParam(value="식당 ID") @PathVariable UUID id
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.getRestaurantReviews(id),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 리뷰 작성", notes = "식당 리뷰 작성")
    @ResponseBody
    @PostMapping("review")
    public ResponseEntity<Long> setRestaurantReview(
            @ApiParam(value="식당 ID") @RequestBody RestaurantReviewSaveDto restaurantReviewSaveDto,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.saveRestaurantReview(restaurantReviewSaveDto, firebaseUuid),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 리뷰 삭제", notes = "식당 리뷰 삭제")
    @ResponseBody
    @DeleteMapping("review/{id}")
    public ResponseEntity<Long> deleteRestaurantReview(
            @ApiParam(value="식당 리뷰 ID") @PathVariable Long id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.deleteRestaurantReview(id, firebaseUuid),
                HttpStatus.OK
        );
    }


}
