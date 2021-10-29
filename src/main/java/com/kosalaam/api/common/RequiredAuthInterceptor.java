package com.kosalaam.api.common;

import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@Log4j2
public class RequiredAuthInterceptor extends HandlerInterceptorAdapter {

    private final AuthUtils authUtils;

    /**
     * request header 의 token 을 확인하고
     * request attribute 에 firebaseUid 를 세팅한다.
     * token 이 유효하지 않다면 에러를 발생시킨다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("----------");
        System.out.println("required preHandle");
        System.out.println("----------");

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String uid = authUtils.checkToken(token);
        log.debug("firebase uuid: "+ uid);
        request.setAttribute("firebaseUuid", uid);
        return super.preHandle(request, response, handler);

    }

}
