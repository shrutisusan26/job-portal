package com.programming.applicationservice.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class FilterService {
    private static final String SECRET_KEY="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public Key getSignInKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
