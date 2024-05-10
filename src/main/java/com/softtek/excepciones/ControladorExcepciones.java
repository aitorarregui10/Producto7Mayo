package com.softtek.excepciones;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class ControladorExcepciones extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String mensaje = ex.getBindingResult().getAllErrors().stream().map(e -> {
            return e.getDefaultMessage().concat(",");
        }).collect(Collectors.joining());
        ExcepcionRespuesta e1 = new ExcepcionRespuesta(LocalDateTime.now(),
                mensaje, request.getDescription(false));
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(ExcepcionPersonalizadaNoEncontrado.class)
    public ResponseEntity<ExcepcionRespuesta>
    catchDeNoEncontrado(ExcepcionPersonalizadaNoEncontrado ex, WebRequest peticion){
        //Crea objeto de ExcepcionRespuesta
        ExcepcionRespuesta e1 = new ExcepcionRespuesta(LocalDateTime.now(), ex.getMessage(),
                peticion.getDescription(false));
        //Devolvemos la respuesta
        return new ResponseEntity<>(e1, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExcepcionRespuesta> catchTodasLasExcepciones
            (Exception ex, WebRequest peticion){
        //Creamos objeto de Excepcion Respuesta
        ExcepcionRespuesta e1 = new ExcepcionRespuesta(LocalDateTime.now(), ex.getMessage(),
                peticion.getDescription(false));
        // La devolvemos
        return new ResponseEntity<>(e1, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
