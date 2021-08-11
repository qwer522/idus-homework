package com.idus.homework.common.handler;

import com.idus.homework.common.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthErrorHandlerController {

    @RequestMapping("/401")
    public ApiResponse<?> notFoundError() {
        return ApiResponse.fail(HttpServletResponse.SC_UNAUTHORIZED, "인증이 올바르지 않습니다.", "Auth Error");
    }
}