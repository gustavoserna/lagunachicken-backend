package com.digitaul.lagunachicken.exception;

import com.digitaul.lagunachicken.exception.types.DuplicatedEntryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class JsonExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(Exception exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicatedEntryException.class)
    @ResponseBody
    public ResponseEntity<Object> handleDuplicatedEntryException(DuplicatedEntryException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

}