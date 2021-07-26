package com.kosalaam.api.web.prayerroom;

import com.kosalaam.api.web.prayerroom.dto.PrayerroomRespDto;
import com.kosalaam.api.web.prayerroom.dto.PrayerroomReviewRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Prayer Room")
@RequestMapping("/api/prayerroom")
@RestController
public class PrayerroomController {
    @ApiOperation(value = "기도실 리스트 조회", notes = "반경 5km 이내의 기도실 리스트를 조회")
    @GetMapping
    public ResponseEntity<List<PrayerroomRespDto>> getAccomodations(
            @ApiParam(value="현재 위치 위도값") @RequestParam double latitude,
            @ApiParam(value="현재 위치 경도값") @RequestParam double longitude
    ) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 정보 조회", notes = "기도실 ID로 상세 정보 조회")
    @GetMapping("info")
    public ResponseEntity<PrayerroomRespDto> getPrayerroom(
            @ApiParam(value="기도실 ID") @RequestParam Long id
    ) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 좋아요 등록", notes = "기도실에 좋아요 등록")
    @ResponseBody
    @PostMapping("like")
    public void setPrayerroomLike(
            @ApiParam(value="기도실 ID") @RequestBody Long id
    ) throws Exception {}

    @ApiOperation(value = "기도실 좋아요 취소", notes = "기도실 좋아요 취소")
    @DeleteMapping("like")
    public void deletePrayerroomLike(
            @ApiParam(value="기도실 ID") @RequestParam Long id
    ) throws Exception {}

    @ApiOperation(value = "기도실 리뷰 조회", notes = "기도실 리뷰 조회")
    @GetMapping("review")
    public ResponseEntity<List<PrayerroomReviewRespDto>> setPrayerroomReview(
            @ApiParam(value="기도실 ID") @RequestParam Long id
    ) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 리뷰 작성", notes = "기도실 리뷰 작성")
    @ResponseBody
    @PostMapping("review")
    public void setPrayerroomReview(
            @ApiParam(value="기도실 ID") @RequestBody Long id,
            @ApiParam(value="리뷰 내용") @RequestBody String comment
    ) throws Exception {}
}
