package com.kosalaam.api.common;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class UnRequiredAuthInterceptor extends HandlerInterceptorAdapter {

    private final AuthUtils authUtils;

    /**
     * request header 의 token 을 확인하고
     * request attribute 에 firebaseUid 를 세팅한다.
     * token 이 유효하지 않다면 attribute 에 null 을 세팅한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("----------");
        System.out.println("unrequired preHandle");
        System.out.println("----------");

        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            request.setAttribute("firebaseUuid", authUtils.checkToken(token));
        } catch (UnauthorizedException ue){
            request.setAttribute("firebaseUuid", "");
        } catch (FirebaseAuthException fe) {
            request.setAttribute("firebaseUuid", "");
        } catch (Exception e) {
            throw e;
        }

        return super.preHandle(request, response, handler);

    }
}
