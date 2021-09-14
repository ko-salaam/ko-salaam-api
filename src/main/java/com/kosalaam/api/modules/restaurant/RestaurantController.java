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

@RequiredArgsConstructor
@Api(tags = "Restaurant")
@RequestMapping("/api/restaurant")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @ApiOperation(value = "식당 리스트 조회", notes = "반경 5km 이내의 식당 리스트를 조회")
    @GetMapping
    public ResponseEntity<List<RestaurantRespDto>> getRestaurants(
            @ApiParam(value="현재 위치 위도값", defaultValue = "37.413294") @RequestParam(defaultValue = "37.413294") double latitude,
            @ApiParam(value="현재 위치 경도값", defaultValue = "127.269311") @RequestParam(defaultValue = "127.269311") double longitude,
            @ApiParam(value="페이지 번호", defaultValue = "1") @RequestParam(defaultValue = "1") int pageNum,
            @ApiParam(value="페이지 사이즈", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize
    ) throws Exception {
        return new ResponseEntity<>(restaurantService.getRestaurants(latitude, longitude, pageNum, pageSize),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 정보 조회", notes = "식당 ID로 상세 정보 조회")
    @GetMapping("{id}")
    public ResponseEntity<RestaurantRespDto> getRestaurant(
            @ApiIgnore @RequestHeader(value="Authorization", required = false) String token,
            @ApiParam(value="식당 ID") @PathVariable Long id
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.getRestaurant(id, token),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 정보 수정", notes = "식당 ID로 상세 정보 수정")
    @PutMapping("{id}")
    public ResponseEntity updateRestaurant(
            @ApiParam(value="식당 ID") @PathVariable Long id,
            @ApiParam(value="수정할 정보") @RequestBody RestaurantUpdateDto restaurantUpdateReqDto
    ) throws Exception {
        restaurantService.updateRestaurant(id, restaurantUpdateReqDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 등록", notes = "식당 등록")
    @PostMapping
    public ResponseEntity saveRestaurant (
            @RequestBody RestaurantSaveDto restaurantSaveDto) throws Exception {
        restaurantService.saveRestaurant(restaurantSaveDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 삭제", notes = "식당 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity deleteRestaurant (
            @ApiParam(value="식당 ID") @PathVariable Long id) throws Exception {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 좋아요 등록", notes = "식당에 좋아요 등록")
    @ResponseBody
    @PostMapping("like/{id}")
    public ResponseEntity setRestaurantLike(
            @ApiIgnore @RequestHeader(value="Authorization", required = false) String token,
            @ApiParam(value="식당 ID") @PathVariable Long id
    ) throws Exception {
        restaurantService.setLikeRestaurant(id, token);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 좋아요 취소", notes = "식당 좋아요 취소")
    @DeleteMapping("like/{id}")
    public ResponseEntity deleteRestaurantLike(
            @ApiIgnore @RequestHeader(value="Authorization", required = false) String token,
            @ApiParam(value="식당 ID") @PathVariable Long id
    ) throws Exception {
        restaurantService.deleteLikeRestaurant(id, token);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 리뷰 조회", notes = "식당 리뷰 조회")
    @GetMapping("review/{id}")
    public ResponseEntity<RestaurantReviewsRespDto> setRestaurantReview(
            @ApiParam(value="식당 ID") @PathVariable Long id
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
            @ApiIgnore @RequestHeader(value="Authorization") String token,
            @ApiParam(value="식당 ID") @RequestBody RestaurantReviewSaveDto restaurantReviewSaveDto
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.saveRestaurantReview(restaurantReviewSaveDto, token),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 리뷰 삭제", notes = "식당 리뷰 삭제")
    @ResponseBody
    @DeleteMapping("review/{id}")
    public ResponseEntity<Long> deleteRestaurantReview(
            @ApiIgnore @RequestHeader(value="Authorization") String token,
            @ApiParam(value="식당 리뷰 ID") @PathVariable Long id
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.deleteRestaurantReview(id, token),
                HttpStatus.OK
        );
    }


}
