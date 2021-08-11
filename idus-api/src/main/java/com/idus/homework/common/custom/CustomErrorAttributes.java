package com.idus.homework.common.custom;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorAttributes implements ErrorAttributes {
    private static final String ERROR_INTERNAL_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, options.isIncluded(ErrorAttributeOptions.Include.STACK_TRACE));
        return errorAttributes;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        Throwable exception = getAttribute(webRequest, ERROR_INTERNAL_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(webRequest, RequestDispatcher.ERROR_EXCEPTION);
        }
        // store the exception in a well-known attribute to make it available to metrics
        // instrumentation.
        webRequest.setAttribute(ErrorAttributes.ERROR_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        return exception;
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("success", "false");
        addStatus(errorAttributes, webRequest);
        addErrorDetails(errorAttributes, webRequest, includeStackTrace);
        return errorAttributes;
    }

    private void addStatus(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        Integer status = getAttribute(requestAttributes, RequestDispatcher.ERROR_STATUS_CODE);
        if (status == null) {
            errorAttributes.put("code", "999");
            errorAttributes.put("result", "None");
            return;
        }
        errorAttributes.put("code", status.toString());
        try {
            errorAttributes.put("result", HttpStatus.valueOf(status).getReasonPhrase());
        }
        catch (Exception ex) {
            // Unable to obtain a reason
            errorAttributes.put("result", "Http Status " + status);
        }
    }

    private void addErrorDetails(Map<String, Object> errorAttributes, WebRequest webRequest,
                                 boolean includeStackTrace) {
        Throwable error = getError(webRequest);
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
            if (includeStackTrace) {
                addStackTrace(errorAttributes, error);
            }
        }
        addErrorMessage(errorAttributes, webRequest, error);
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest, Throwable error) {
        BindingResult result = extractBindingResult(error);
        if (result == null) {
            addExceptionErrorMessage(errorAttributes, webRequest, error);
        }
        else {
            addBindingResultErrorMessage(errorAttributes, result);
        }
    }

    private void addExceptionErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest, Throwable error) {
        errorAttributes.put("message", getMessage(webRequest, error));
    }

    protected String getMessage(WebRequest webRequest, Throwable error) {
        Object message = getAttribute(webRequest, RequestDispatcher.ERROR_MESSAGE);
        if (!ObjectUtils.isEmpty(message)) {
            return message.toString();
        }
        if (error != null && StringUtils.hasLength(error.getMessage())) {
            return error.getMessage();
        }
        return "No message available";
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    private void addBindingResultErrorMessage(Map<String, Object> errorAttributes, BindingResult result) {
        errorAttributes.put("message", "Validation failed for object='" + result.getObjectName() + "'. "
            + "Error count: " + result.getErrorCount());
        errorAttributes.put("result", result.getAllErrors());
    }

    private BindingResult extractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        }
        if (error instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) error).getBindingResult();
        }
        return null;
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
    }



}
