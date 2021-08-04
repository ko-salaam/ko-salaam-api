package com.kosalaam.api.modules.restaurant;

import com.kosalaam.api.modules.restaurant.dto.RestaurantRespDto;
import com.kosalaam.api.modules.restaurant.dto.RestaurantReviewRespDto;
import com.kosalaam.api.modules.restaurant.dto.RestaurantSaveReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiParam(value="현재 위치 위도값", defaultValue = "127.269311") @RequestParam(defaultValue = "127.269311") double latitude,
            @ApiParam(value="현재 위치 경도값", defaultValue = "37.413294") @RequestParam(defaultValue = "37.413294") double longitude,
            @ApiParam(value="페이지 번호", defaultValue = "1") @RequestParam(defaultValue = "1") int pageNum,
            @ApiParam(value="페이지 사이즈", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize
    ) throws Exception {
        return new ResponseEntity<>(restaurantService.getRestaurants(pageNum, pageSize),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 정보 조회", notes = "식당 ID로 상세 정보 조회")
    @GetMapping("{id}")
    public ResponseEntity<RestaurantRespDto> getRestaurant(
            @ApiParam(value="식당 ID") @PathVariable Long id
    ) throws Exception {
        return new ResponseEntity<>(
                restaurantService.getRestaurant(id),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "식당 등록", notes = "식당 등록")
    @PostMapping("info")
    public Long saveRestaurant(
            @RequestBody RestaurantSaveReqDto restaurantSaveReqDto) {
        System.out.println("test----");
        return restaurantService.save(restaurantSaveReqDto);
    }

    @ApiOperation(value = "식당 좋아요 등록", notes = "식당에 좋아요 등록")
    @ResponseBody
    @PostMapping("like")
    public void setRestaurantLike(
            @ApiParam(value="식당 ID") @RequestBody Long id
    ) throws Exception {}

    @ApiOperation(value = "식당 좋아요 취소", notes = "식당 좋아요 취소")
    @DeleteMapping("like")
    public void deleteRestaurantLike(
            @ApiParam(value="식당 ID") @RequestParam Long id
    ) throws Exception {}

    @ApiOperation(value = "식당 리뷰 조회", notes = "식당 리뷰 조회")
    @GetMapping("review")
    public ResponseEntity<List<RestaurantReviewRespDto>> setRestaurantReview(
            @ApiParam(value="식당 ID") @RequestParam Long id
    ) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "식당 리뷰 작성", notes = "식당 리뷰 작성")
    @ResponseBody
    @PostMapping("review")
    public void setRestaurantReview(
            @ApiParam(value="식당 ID") @RequestBody Long id,
            @ApiParam(value="리뷰 내용") @RequestBody String comment
    ) throws Exception {}

}
