package com.kosalaam.api.common;

import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class RequiredAuthInterceptor extends HandlerInterceptorAdapter {

    private final AuthUtils authUtils;

    /**
     * request header 의 token 을 확인하고
     * request attribute 에 firebaseUid 를 세팅한다.
     * token 이 유효하지 않다면 에러를 발생시킨다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String uid = authUtils.checkToken(token);
        request.setAttribute("firebaseUid", uid);
        return super.preHandle(request, response, handler);

    }

}
