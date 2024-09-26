package com.example.jpaonetomany.services;

import com.example.jpaonetomany.model.Kommune;
import com.example.jpaonetomany.model.Kommune;
import com.example.jpaonetomany.repositories.KommuneRepository;
import com.example.jpaonetomany.repositories.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service

public class ApiServiceGetKommuneImpl implements ApiServiceGetKommune {
    private  final RestTemplate restTemplate;
    public ApiServiceGetKommuneImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";
    @Autowired
    KommuneRepository kommuneRepository;

    @Override
    public List<Kommune> getKommune() {
        List<Kommune> lst = new ArrayList<>();
        ResponseEntity<List<Kommune>> kommuneResponse = restTemplate.exchange(kommuneUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Kommune>>() {
        });
        List<Kommune> kommuner = kommuneResponse.getBody();
        savekommuner(kommuner);
        return kommuner;
    }

    private void savekommuner(List<Kommune> kommuner) {
        kommuner.forEach(reg -> kommuneRepository.save(reg));
    }

}
