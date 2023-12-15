package com.devsu.clientes.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Map<String, String> SQL_STATE_MESSAGES;

    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("23000", "Violación de restricción de integridad. Unique Key");
        aMap.put("23505", "Error: Violación de restricción única.");
        aMap.put("23502", "Error: Violación de campo obligatorio no nulo.");
        aMap.put("23503", "Error: Violación de restricción de clave foránea.");
        aMap.put("22001", "Error: Datos demasiado largos para una columna.");
        aMap.put("42000", "Error de sintaxis o violación de regla de acceso.");
        aMap.put("08003", "La conexión con la base de datos no existe.");
        aMap.put("08004", "La conexión con la base de datos ha sido rechazada.");
        aMap.put("08007", "Estado desconocido de la transacción.");
        aMap.put("40001", "Error de serialización en la transacción.");
        SQL_STATE_MESSAGES = Collections.unmodifiableMap(aMap);
    }

    @ExceptionHandler(CustomHandlerException.class)
    public ResponseEntity<Object> handlerCustomExceptions(CustomHandlerException e){

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String mensaje = error.getDefaultMessage();
            errores.put("error", mensaje);
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>>  handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Map<String, String> errores = new HashMap<>();

        Throwable rootCause = e.getRootCause();
        if(rootCause instanceof SQLException){
            SQLException sqlEx = (SQLException) rootCause;
            String sqlState = sqlEx.getSQLState();

            String mensaje = SQL_STATE_MESSAGES.getOrDefault(sqlState, "Error en la base de datos.");
            errores.put("mensaje", mensaje);

        }
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
