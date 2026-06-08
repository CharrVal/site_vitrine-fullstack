package com.artisan.vitrine.Presentation.controller;

import com.artisan.vitrine.Presentation.dto.ContactRequest;
import com.artisan.vitrine.Business.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:4000")
public class ContactController {
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendContactEmail(@RequestBody ContactRequest request) {
        try {
            String body = request.getMessage();
            emailService.sendEmail(request.getEmail(), "Nouveau message de " + request.getName(), body);
            return ResponseEntity.ok("Message envoyé (sandbox actif)");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'envoi du message");
        }
    }
}