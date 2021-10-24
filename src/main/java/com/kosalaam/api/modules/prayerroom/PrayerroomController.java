package com.kosalaam.api.modules.prayerroom;

import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomDto;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomReviewSaveDto;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomReviewsRespDto;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomSaveDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Api(tags = "Prayer Room")
@RequestMapping("/api/prayerroom")
@RestController
public class PrayerroomController {

    private final HttpServletRequest request;

    private final PrayerroomService prayerroomService;

    @ApiOperation(value = "기도실 리스트 조회", notes = "반경 Nkm 이내의 기도실 리스트를 조회")
    @GetMapping
    public ResponseEntity<List<PlaceDto>> getPrayerrooms(
            @ApiParam(value="현재 위치 위도값", defaultValue = "37.498095") @RequestParam(defaultValue = "37.498095") double latitude,
            @ApiParam(value="현재 위치 경도값", defaultValue = "127.027610") @RequestParam(defaultValue = "127.027610") double longitude,
            @ApiParam(value="반경 N km", defaultValue = "5") @RequestParam(defaultValue = "5") int distance,
            @ApiParam(value="검색 키워드") @RequestParam(required = false) String keyword,
            @ApiParam(value="페이지 번호", defaultValue = "0") @RequestParam(defaultValue = "0") int pageNum,
            @ApiParam(value="페이지 사이즈", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(prayerroomService.getPrayerrooms(latitude, longitude, distance, keyword, pageNum, pageSize, firebaseUuid),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "기도실 정보 조회", notes = "기도실 ID로 상세 정보 조회")
    @GetMapping("{id}")
    public ResponseEntity<PrayerroomDto> getPrayerroom(
            @ApiParam(value="기도실 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(
                prayerroomService.getPrayerroom(id, firebaseUuid),
                HttpStatus.OK
        );
    }

//    @ApiOperation(value = "기도실 등록", notes = "기도실 등록")
//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PostMapping
    public ResponseEntity<PrayerroomDto> savePrayerroom (
            @ModelAttribute PrayerroomSaveDto prayerroomSaveDto,
            @RequestPart List<MultipartFile> imageFiles
            ) throws Exception {
        return new ResponseEntity(prayerroomService.savePrayerroom(request, prayerroomSaveDto, imageFiles), HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 정보 수정", notes = "기도실 ID로 상세 정보 수정")
    @PutMapping("{id}")
    public ResponseEntity updatePrayerroom(
            @ApiParam(value="기도실 ID") @PathVariable UUID id,
            @ApiParam(value="수정할 정보") @RequestBody PrayerroomDto PrayerroomDto
    ) throws Exception {
        return new ResponseEntity(prayerroomService.updatePrayerroom(id, PrayerroomDto), HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 삭제", notes = "기도실 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity deletePrayerroom (
            @ApiParam(value="기도실 ID") @PathVariable UUID id) throws Exception {
        prayerroomService.deletePrayerroom(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 좋아요 등록", notes = "기도실에 좋아요 등록")
    @ResponseBody
    @PostMapping("like/{id}")
    public ResponseEntity setPrayerroomLike(
            @ApiParam(value="기도실 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        prayerroomService.setLikePrayerroom(id, firebaseUuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 좋아요 취소", notes = "기도실 좋아요 취소")
    @DeleteMapping("like/{id}")
    public ResponseEntity deletePrayerroomLike(
            @ApiParam(value="기도실 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        prayerroomService.deleteLikePrayerroom(id, firebaseUuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "기도실 리뷰 조회", notes = "기도실 리뷰 조회")
    @GetMapping("review/{id}")
    public ResponseEntity<PrayerroomReviewsRespDto> setPrayerroomReview(
            @ApiParam(value="기도실 ID") @PathVariable UUID id
    ) throws Exception {
        return new ResponseEntity<>(
                prayerroomService.getPrayerroomReviews(id),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "기도실 리뷰 작성", notes = "기도실 리뷰 작성")
    @ResponseBody
    @PostMapping("review")
    public ResponseEntity<Long> setPrayerroomReview(
            @ApiParam(value="기도실 ID") @RequestBody PrayerroomReviewSaveDto prayerroomReviewSaveDto,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(
                prayerroomService.savePrayerroomReview(prayerroomReviewSaveDto, firebaseUuid),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "기도실 리뷰 삭제", notes = "기도실 리뷰 삭제")
    @ResponseBody
    @DeleteMapping("review/{id}")
    public ResponseEntity<Long> deletePrayerroomReview(
            @ApiParam(value="기도실 리뷰 ID") @PathVariable Long id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(
                prayerroomService.deletePrayerroomReview(id, firebaseUuid),
                HttpStatus.OK
        );
    }
}
