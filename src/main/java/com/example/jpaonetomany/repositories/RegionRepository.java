package com.example.jpaonetomany.repositories;

import com.example.jpaonetomany.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, String> {
    List<Region> findByKode(String kode);
    void deleteByKode(String kode);

}
