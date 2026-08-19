package com.lite.hris;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello(){
        return "Hello...";
    }

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt){
        return Map.of(
                "username",jwt.getClaimAsString("preferred_username"),
                "email",jwt.getClaimAsString("email"),
                "name",jwt.getClaimAsString("name")
        );
    }
}
