package com.artisan.vitrine.security.Jwt;

import com.artisan.vitrine.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthenticationResponse {
    private String token;
    private User user;
}
