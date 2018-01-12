package edu.sample.common.exeption;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.LOWEST_PRECEDENCE)
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Called whenever an @Valid annotation on a bean method (typically a controller method) fails.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildExceptionResponse(status, request, ex, "");
    }	

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> unknownException(HttpServletRequest req, Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return buildExceptionResponse(status, req, e);
    }
    
    protected ResponseEntity<Object> buildExceptionResponse(HttpStatus status, WebRequest request, Exception e, String errorMessage) {
        ServletWebRequest webReq = (ServletWebRequest) request;
        HttpServletRequest req = webReq.getRequest();
        return buildExceptionResponse(status, req, e, errorMessage);
    }

    protected ResponseEntity<Object> buildExceptionResponse(HttpStatus status, HttpServletRequest req, Exception e) {
        return buildExceptionResponse(status, req, e, e.getMessage());
    }

    protected ResponseEntity<Object> buildExceptionResponse(HttpStatus status, HttpServletRequest req, Exception e, String errorMessage) {
        logger.error("Exception from controller", e);      
        return new ResponseEntity<>(null, status);
    }

    
}
