package com.alexvak.booklibrary.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping("/token")
    public Map<String, String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }

    @GetMapping(value = "/checkSession")
    public ResponseEntity<SimpleResponse> checkSession() {
        return new ResponseEntity<>(new SimpleResponse("Session Active!"), HttpStatus.OK);
    }

    @PostMapping(value = "/user/logout")
    public ResponseEntity<SimpleResponse> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(new SimpleResponse("Logout Successfully!"), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    private class SimpleResponse {
        private String message;
    }
}
