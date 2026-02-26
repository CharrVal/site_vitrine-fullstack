package com.artisan.vitrine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    private String name;
    private String email;
    private String message;

    public ContactRequest(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

}
