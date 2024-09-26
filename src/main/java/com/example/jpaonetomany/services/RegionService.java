package com.example.jpaonetomany.services;

import com.example.jpaonetomany.model.Region;
import com.example.jpaonetomany.repositories.RegionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Transactional
    public void deleteRegionByKode(String kode) {
        Optional<Region> region = regionRepository.findByKode(kode).stream().findFirst();
        if (region.isPresent()) {
            regionRepository.delete(region.get());
        } else {
            throw new RuntimeException("Region not found for kode: " + kode);
        }
    }
}
