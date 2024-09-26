package com.example.jpaonetomany.controllers;

import com.example.jpaonetomany.model.Region;
import com.example.jpaonetomany.repositories.RegionRepository;
import com.example.jpaonetomany.services.ApiServiceGetRegions;
import com.example.jpaonetomany.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegions apiServiceGetRegions;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    private RegionService regionService;



    @GetMapping("/regions")
    public List<Region> getRegions() {
        return apiServiceGetRegions.getRegions();
    }

    @GetMapping("/getregions")
    public List<Region> getOwnRegions() {
        return regionRepository.findAll();
    }
    @PutMapping("/putregions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Region> putRegion(@RequestBody Region region) {

    regionRepository.save(region);
    return ResponseEntity.ok(region);


    }
    @PostMapping("/postregions")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        return regionRepository.save(region);
    }

    @DeleteMapping("/deleteregions/{kode}")
    public ResponseEntity<String> deleteRegion(@PathVariable String kode) {
        regionService.deleteRegionByKode(kode);
        return ResponseEntity.ok("Region deleted");
    }
}



