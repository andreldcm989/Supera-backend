package br.com.banco.controller.exception;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseErrorFormatter implements Serializable{
    
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
