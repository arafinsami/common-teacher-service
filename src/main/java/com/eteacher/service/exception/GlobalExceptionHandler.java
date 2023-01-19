package com.eteacher.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   /* @ExceptionHandler({Exception.class})
    public ResponseEntity<JSONObject> handleGlobalExceptions(Exception ex, WebRequest request) {
        log.error("Unknown exception.", ex);
        if (ex instanceof InvalidFormatException) {
            return new ResponseEntity<>(ResponseBuilder.error(ex.getMessage()).getJson(), HttpStatus.BAD_REQUEST);
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return new ResponseEntity<>(ResponseBuilder.error(ex.getMessage()).getJson(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResponseBuilder.error((INTERNAL_SERVER_ERROR)).getJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<JSONObject> handleNotFoundExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((ex.getMessage() + " " + RESOURCE_NOT_FOUND)).getJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<JSONObject> handleBadRequestExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((ex.getMessage())).getJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<JSONObject> handleNumberFormatExceptions(InvalidFormatException ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((NUMBER_FORMAT_EXCEPTION)).getJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1] + ":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity(msg.toString(), HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

