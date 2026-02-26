package com.artisan.vitrine.controller;

import com.artisan.vitrine.dto.ContactRequest;
import com.artisan.vitrine.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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