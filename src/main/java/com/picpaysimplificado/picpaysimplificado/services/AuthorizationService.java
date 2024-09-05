package com.picpaysimplificado.picpaysimplificado.services;


import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.picpaysimplificado.domain.user.User;


@Service
public class AuthorizationService {
    @Autowired
    private RestTemplate restTemplate;

    private String authApiUrl;

    public boolean authorizeTransaction(User sender, BigDecimal value){
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(this.authApiUrl, Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            @SuppressWarnings("null")
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}

