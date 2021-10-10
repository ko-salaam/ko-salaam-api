package com.kosalaam.api.modules.accommodation;

import com.kosalaam.api.modules.accommodation.dto.AccommodationDto;
import com.kosalaam.api.modules.accommodation.dto.AccommodationReviewRespDto;
import com.kosalaam.api.modules.accommodation.dto.AccommodationReviewSaveDto;
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
@Api(tags = "Accommodation")
@RequestMapping("/api/accommodation")
@RestController
public class AccommodationController {

    private final AccommodationService accommodationService;

    @ApiOperation(value = "숙소 리스트 조회", notes = "반경 5km 이내의 숙소 리스트를 조회")
    @GetMapping
    public ResponseEntity<List<AccommodationDto>> getAccomodations(
            @ApiParam(value="현재 위치 위도값", defaultValue = "37.498095") @RequestParam(defaultValue = "37.498095") double latitude,
            @ApiParam(value="현재 위치 경도값", defaultValue = "127.027610") @RequestParam(defaultValue = "127.027610") double longitude,
            @ApiParam(value="반경 N km", defaultValue = "5") @RequestParam(defaultValue = "5") int distance,
            @ApiParam(value="검색 키워드") @RequestParam(required = false) String keyword,
            @ApiParam(value="페이지 번호", defaultValue = "0") @RequestParam(defaultValue = "0") int pageNum,
            @ApiParam(value="페이지 사이즈", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(accommodationService.getAccommodations(latitude,longitude,distance, keyword, pageNum, pageSize, firebaseUuid), HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 정보 조회", notes = "숙소 ID로 상세 정보 조회")
    @GetMapping("{id}")
    public ResponseEntity<AccommodationDto> getAccommodation(
            @ApiParam(value="숙소 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(accommodationService.getAccommodation(id, firebaseUuid), HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 등록", notes = "숙소 등록")
    @PostMapping
    public ResponseEntity<AccommodationDto> saveAccommodation(
            @RequestBody AccommodationDto accommodationDto) throws Exception {
        return new ResponseEntity(accommodationService.saveAccommodation(accommodationDto), HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 정보 수정", notes = "숙소 ID로 상세 정보 수정")
    @PutMapping("{id}")
    public ResponseEntity updateAccommodation(
            @ApiParam(value="숙소 ID") @PathVariable UUID id,
            @ApiParam(value="수정할 정보") @RequestBody AccommodationDto accommodationDto
    ) throws Exception {
        return new ResponseEntity(accommodationService.updateAccommodation(id, accommodationDto), HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 삭제", notes = "숙소 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity deleteAccommodation (
            @ApiParam(value="숙소 ID") @PathVariable UUID id
    ) throws Exception {
        accommodationService.deleteAccommodation(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 좋아요 등록", notes = "숙소에 좋아요 등록")
    @ResponseBody
    @PostMapping("like/{id}")
    public ResponseEntity setAccommodationLike(
            @ApiParam(value="숙소 ID") @PathVariable UUID id,
            @ApiIgnore(value="Firebase UUID") @RequestAttribute("firebaseUuid") String firebaseUuid
    ) throws Exception {
        accommodationService.setLikeAccommodation(id, firebaseUuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 좋아요 취소", notes = "숙소 좋아요 취소")
    @DeleteMapping("like/{id}")
    public ResponseEntity deleteAccommodationLike(
            @ApiParam(value="숙소 ID") @PathVariable UUID id,
            @ApiIgnore(value="Firebase UUID") @RequestAttribute("firebaseUuid") String firebaseUuid
    ) throws Exception {
        accommodationService.deleteLikeAccommodation(id, firebaseUuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 리뷰 조회", notes = "숙소 리뷰 조회")
    @GetMapping("review/{id}")
    public ResponseEntity<List<AccommodationReviewRespDto>> setAccommodationReview(
            @ApiParam(value="숙소 ID") @PathVariable UUID id
    ) throws Exception {
        return new ResponseEntity(accommodationService.getAccommodationReviews(id), HttpStatus.OK);
    }

    @ApiOperation(value = "숙소 리뷰 작성", notes = "숙소 리뷰 작성")
    @ResponseBody
    @PostMapping("review")
    public ResponseEntity setAccommodationReview(
            @ApiParam(value="숙소 ID") @RequestBody AccommodationReviewSaveDto accommodationReviewSaveDto,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity(
                accommodationService.saveAccommodationReview(accommodationReviewSaveDto, firebaseUuid),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "숙소 리뷰 삭제", notes = "숙소 리뷰 삭제")
    @ResponseBody
    @PostMapping("review/{id}")
    public ResponseEntity deleteAccommodationReview(
            @ApiParam(value="삭제 ID") @PathVariable Long id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity(
                accommodationService.deleteAccommodationReview(id, firebaseUuid),
                HttpStatus.OK
        );
    }

}
