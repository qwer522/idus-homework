package com.idus.homework.common.handler;

import com.idus.homework.common.custom.CustomErrorAttributes;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorHandlerController extends AbstractErrorController implements ErrorController {

    private final CustomErrorAttributes errorAttributes;

    public ErrorHandlerController(CustomErrorAttributes errorAttributes) {
        super(errorAttributes, Collections.emptyList());
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        WebRequest webRequest = new ServletWebRequest(request);
        ErrorAttributeOptions eao = ErrorAttributeOptions.of(
            ErrorAttributeOptions.Include.BINDING_ERRORS,
            ErrorAttributeOptions.Include.EXCEPTION,
            ErrorAttributeOptions.Include.MESSAGE,
            ErrorAttributeOptions.Include.STACK_TRACE
        );

        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, eao);

        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }

        return new ResponseEntity<>(errorAttributes, status);
    }

}