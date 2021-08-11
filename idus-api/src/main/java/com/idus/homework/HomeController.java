package com.idus.homework;

import com.idus.homework.common.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Default API")
@RestController
public class HomeController {
    @ApiOperation(value = "Default URL")
    @GetMapping("/")
    public ApiResponse<String> home() {
        return ApiResponse.ok("home");
    }
}
