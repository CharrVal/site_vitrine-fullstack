package com.artisan.vitrine.controller;

import com.artisan.vitrine.dto.ActualityRequestDTO;
import com.artisan.vitrine.dto.ActualityResponseDTO;
import com.artisan.vitrine.dto.ProductResponseDTO;
import com.artisan.vitrine.entity.Actuality;
import com.artisan.vitrine.service.ActualityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ActualityResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActualityResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ActualityResponseDTO> createActuality(@Valid @RequestBody ActualityRequestDTO dto) {
        ActualityResponseDTO created = service.createActuality(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActualityResponseDTO> updateActuality(@PathVariable Long id, @Valid @RequestBody ActualityRequestDTO dto) {
        ActualityResponseDTO response = service.updateActuality(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActualityById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
