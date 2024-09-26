package com.example.jpaonetomany.services;

import com.example.jpaonetomany.model.Region;
import com.example.jpaonetomany.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetRegionImpl implements ApiServiceGetRegions {
    private RestTemplate restTemplate;

    public ApiServiceGetRegionImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String regionUrl = "https://api.dataforsyningen.dk/regioner";
    @Autowired
    RegionRepository regionRepository;

    @Override
    public List<Region> getRegions() {
        List<Region> lst = new ArrayList<>();
        // RestTemplate.exchange method is used to make a request to the API ( HTTP GET) to the Region url
        // Response is stored in ResponseEntity<List<Region>> regionResponse
        // null means that there is no body send with the request
        // new ParameterizedTypeReference<List<Region>>() {} is used to sepcify that the json read is a Array and therefor List is used
        //
        ResponseEntity<List<Region>> regionResponse = restTemplate.exchange(regionUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Region>>() {
        });
        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
        return regioner;
    }

    private void saveRegioner(List<Region> regioner) {
        regioner.forEach(reg -> regionRepository.save(reg));
    }

}
