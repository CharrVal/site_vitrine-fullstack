package com.artisan.vitrine.controller;

import com.artisan.vitrine.entity.Actuality;
import com.artisan.vitrine.service.ActualityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/actualites")
public class ActualityController {

    private final ActualityService service;

    public ActualityController(ActualityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Actuality>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actuality> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Actuality> createActuality(@Valid @RequestBody Actuality actuality) {
        return ResponseEntity.ok(service.createActuality(actuality));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actuality> updateActuality(@PathVariable Long id, @Valid @RequestBody Actuality actuality) {
        Actuality actualityExisting = service.updateActuality(id, actuality);
        return ResponseEntity.ok(actualityExisting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActuality(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
