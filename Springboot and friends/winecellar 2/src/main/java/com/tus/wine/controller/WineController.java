package com.tus.wine.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tus.wine.dao.WineRepository;
import com.tus.wine.model.Wine;


@RestController
@RequestMapping("/wines")
public class WineController {
	ArrayList<Wine> wineList;

    @Autowired
    private WineRepository wineRepo;

    // Get all wines
    @GetMapping
    public List<Wine> getAllWines() {
        return (List<Wine>) wineRepo.findAll();
    }

    // Get wine by ID
    @GetMapping("/{id}")
    public ResponseEntity<Wine> getWineById(@PathVariable Long id) {
    	 Optional<Wine> optionalWine = wineRepo.findById(id);
         if (optionalWine.isPresent()) {
             return ResponseEntity.ok(optionalWine.get());
         }
         return ResponseEntity.notFound().build();
    }

    // Create a new wine
    @PostMapping
    public ResponseEntity<ArrayList<Wine>> createWine(@RequestBody ArrayList<Wine> wineList) {
    	for(Wine wine: wineList) {
	    	 wineRepo.save(wine);
    	}
    	return ResponseEntity.status(HttpStatus.CREATED).body(wineList);
    }

    // Update an existing wine
    @PutMapping("/{id}")
    public ResponseEntity<Wine> updateWine(@PathVariable Long id, @RequestBody Wine wine) {
        if (wineRepo.existsById(id)) {
            wine.setId(id); // Ensure the ID remains consistent
            Wine updatedWine = wineRepo.save(wine);
            return ResponseEntity.ok(updatedWine);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a wine by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable Long id) {
        if (wineRepo.existsById(id)) {
            wineRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
