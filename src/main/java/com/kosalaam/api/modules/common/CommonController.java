package com.kosalaam.api.modules.common;

import com.kosalaam.api.modules.common.dto.CommonDto;
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
@Api(tags = "Common")
@RequestMapping("/api/common")
@RestController
public class CommonController {

    private final CommonService commonService;

    @ApiOperation(value="통합 리스트 조회", notes = "반경 Nkm 이내의 장소 리스트를 통합 조회")
    @GetMapping
    public ResponseEntity<List<CommonDto>> getCommons(
            @ApiParam(value="현재 위치 위도값", defaultValue = "37.498095") @RequestParam(defaultValue = "37.498095") double latitude,
            @ApiParam(value="현재 위치 경도값", defaultValue = "127.027610") @RequestParam(defaultValue = "127.027610") double longitude,
            @ApiParam(value="반경 N km", defaultValue = "5") @RequestParam(defaultValue = "5") int distance,
            @ApiParam(value="검색 키워드") @RequestParam(required = false) String keyword,
            @ApiParam(value="페이지 번호", defaultValue = "0") @RequestParam(defaultValue = "0") int pageNum,
            @ApiParam(value="페이지 사이즈", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<List<CommonDto>>(HttpStatus.OK);
    }
}
