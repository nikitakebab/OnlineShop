package org.example.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/Hello")
    ResponseEntity<String> suckdickfunc() {
        String penis = "penis";

        return new ResponseEntity<String>(penis, HttpStatus.OK);
    }
}
