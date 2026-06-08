package com.artisan.vitrine.Infrasctructure.security.Jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="username")
public class AuthenticationRequest {
    private String username;
    private String password;

}
