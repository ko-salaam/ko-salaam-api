package com.kosalaam.api.modules.accommodation;

import com.kosalaam.api.modules.accommodation.dto.AccommodationRespDto;
import com.kosalaam.api.modules.accommodation.dto.AccommodationReviewRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Accommodation")
@RequestMapping("/api/accommodation")
@RestController
public class AccommodationController {

    private AccommodationService accommodationService;

    @ApiOperation(value = "숙소 리스트 조회", notes = "반경 5km 이내의 숙소 리스트를 조회")
    @GetMapping
    public ResponseEntity<List<AccommodationRespDto>> getAccomodations(
            @ApiParam(value="현재 위치 위도값") @RequestParam double latitude,
            @ApiParam(value="현재 위치 경도값") @RequestParam double longitude
    ) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 정보 조회", notes = "숙소 ID로 상세 정보 조회")
    @GetMapping("info")
    public ResponseEntity<AccommodationRespDto> getAccommodation(
            @ApiParam(value="숙소 ID") @RequestParam Long id
    ) throws Exception {
        return new ResponseEntity<>(accommodationService.getAccommodation(id), HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 좋아요 등록", notes = "숙소에 좋아요 등록")
    @ResponseBody
    @PostMapping("like")
    public void setAccommodationLike(
            @ApiParam(value="숙소 ID") @RequestBody Long id
    ) throws Exception {}

    @ApiOperation(value = "숙소 좋아요 취소", notes = "숙소 좋아요 취소")
    @DeleteMapping("like")
    public void deleteAccommodationLike(
            @ApiParam(value="숙소 ID") @RequestParam Long id
    ) throws Exception {}

    @ApiOperation(value = "숙소 리뷰 조회", notes = "숙소 리뷰 조회")
    @GetMapping("review")
    public ResponseEntity<List<AccommodationReviewRespDto>> setAccommodationReview(
            @ApiParam(value="숙소 ID") @RequestParam Long id
    ) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 리뷰 작성", notes = "숙소 리뷰 작성")
    @ResponseBody
    @PostMapping("review")
    public void setAccommodationReview(
            @ApiParam(value="숙소 ID") @RequestBody Long id,
            @ApiParam(value="리뷰 내용") @RequestBody String comment
    ) throws Exception {}

}
