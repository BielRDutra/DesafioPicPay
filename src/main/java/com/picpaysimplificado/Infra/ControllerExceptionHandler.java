package com.picpaysimplificado.Infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaysimplificado.dtos.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {


    private static final Object ExceptionDTO = null;
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(EntityNotFoundException exception){
        return ResponseEntity.internalServerError().body(ExceptionDTO);
    }
}
