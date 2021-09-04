package com.kosalaam.api.modules.kouser;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@Api(tags = "User")
@RequestMapping("/api/auth")
@RestController
public class KoUserController {

    private final KoUserService koUserService;

    @ApiOperation(value = "사용자 정보 조회")
    @GetMapping("{uid}")
    public void getUser(
            @ApiParam(value="사용자 ID") @PathVariable String uid
    ) throws Exception {
        koUserService.getUser(uid);
    }

    @ApiOperation(value = "로그인")
    @PostMapping
    public void signIn(
            @ApiIgnore @RequestHeader(value="Authorization") String token
    ) throws Exception {
        koUserService.signIn(token);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/new")
    public void signUp(
            @ApiIgnore @RequestHeader(value="Authorization") String token
    ) throws Exception {
        koUserService.signUp(token);
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping
    public void deleteUser(
            @ApiIgnore @RequestHeader(value="Authorization") String token
    ) throws Exception {
        koUserService.deleteUser(token);
    }
}
