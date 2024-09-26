package com.example.jpaonetomany.controllers;

import com.example.jpaonetomany.model.Kommune;
import com.example.jpaonetomany.repositories.KommuneRepository;
import com.example.jpaonetomany.services.ApiServiceGetKommune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins ="*")
public class KommuneRestController {
    @Autowired
    ApiServiceGetKommune apiServiceGetKommune;
    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/kommuner")
    public List<Kommune> getKommuner() {
        return apiServiceGetKommune.getKommune();
    }
    @GetMapping("/getownkommuner")
    public List<Kommune> getOwnKommuner() {
        return kommuneRepository.findAll();
    }

    @PostMapping("/kommuner")
    public Kommune postKommune(@RequestBody Kommune kommune) {
        return kommuneRepository.save(kommune);
    }

    @PutMapping("/updateKommune/{kode}")
    public ResponseEntity<String> updateKommune(@PathVariable String kode, @RequestBody Kommune updatedKommune) {
        // Check if a Kommune with the given kode exists
        Optional<Kommune> optionalKommune = kommuneRepository.findByKode(kode);

        if (optionalKommune.isPresent()) {
            // Kommune exists, so we update it
            Kommune existingKommune = optionalKommune.get();
            existingKommune.setNavn(updatedKommune.getNavn());  // Update fields as necessary
            existingKommune.setRegion(updatedKommune.getRegion());  // Example field to update
            // Save the updated Kommune
            kommuneRepository.save(existingKommune);
            return ResponseEntity.ok("Kommune updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune not found.");
        }
    }
    @DeleteMapping("/deleteKommune/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {
        Optional<Kommune> optionalKommune = kommuneRepository.findByKode(kode);
        if (optionalKommune.isPresent()) {
            kommuneRepository.delete(optionalKommune.get());
            return ResponseEntity.ok("Kommune deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune not found.");
        }
    }

}
