package com.ms_service.service_microservice;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class serviceController {

    @GetMapping("/hello")
    public ResponseEntity<String> getMethodName() {
        String data = "{ \"data\" : \"hello Service prueba\"}";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data);
    }
}
