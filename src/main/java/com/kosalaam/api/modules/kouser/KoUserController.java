package com.kosalaam.api.modules.kouser;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Api(tags = "User")
@RequestMapping("/api/user")
@RestController
public class KoUserController {

    private final KoUserService koUserService;

    @ApiOperation(value = "사용자 정보 조회")
    @GetMapping("{uid}")
    public void getUser(
            @ApiParam(value="사용자 ID") @PathVariable String uid
    ) throws Exception {
        koUserService.getUser();
    }

}
