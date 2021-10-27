package com.kosalaam.api.modules.kouser;


import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.dto.KoUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@Api(tags = "User")
@RequestMapping("/api/auth")
@RestController
public class KoUserController {

    private final KoUserService koUserService;

    @ApiOperation(value = "사용자 정보 조회")
    @GetMapping("{id}")
    public ResponseEntity<KoUser> getUser(
            @ApiParam(value="사용 ID") @PathVariable Long id
    ) throws Exception {
        return new ResponseEntity<>(koUserService.getUser(id), HttpStatus.OK);
    }

    @ApiOperation(value = "나의 정보 조회")
    @GetMapping("me")
    public ResponseEntity<KoUser> getMe(
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(koUserService.getMe(firebaseUuid), HttpStatus.OK);
    }

    @ApiOperation(value = "로그인")
    @PostMapping
    public ResponseEntity<KoUser> signIn(
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(koUserService.signIn(firebaseUuid), HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/new")
    public ResponseEntity<KoUserDto> signUp(
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(koUserService.signUp(firebaseUuid), HttpStatus.OK);
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping
    public ResponseEntity deleteUser(
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        koUserService.deleteUser(firebaseUuid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 정보 수정")
    @PutMapping
    public ResponseEntity<KoUserDto> updateUser(
            @ApiParam(value="수정할 정보") @RequestBody KoUserDto koUserDto,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(koUserService.updateUser(koUserDto, firebaseUuid), HttpStatus.OK);
    }
}
