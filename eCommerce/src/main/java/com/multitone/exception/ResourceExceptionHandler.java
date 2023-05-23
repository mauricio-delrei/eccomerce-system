package com.multitone.exception;


import com.multitone.service.exception.DataIntegrityException;
import com.multitone.service.exception.FileException;
import com.multitone.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException onfe, HttpServletRequest request) {

        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found",
                onfe.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stde);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException die, HttpServletRequest request) {

        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value()
                , "Data Integrity", die.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException manve,
                                                                         HttpServletRequest request) {

        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error", manve.getMessage(), request.getRequestURI());
        for (FieldError fieldError : manve.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }



    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException onfe, HttpServletRequest request) {

        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value()
                , "File error", onfe.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

}
