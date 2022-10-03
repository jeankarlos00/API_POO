package br.iesb.cco.apipoo.controller;

import br.iesb.cco.apipoo.dto.UserDTO;
import br.iesb.cco.apipoo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user) {
        String token = service.login(user);

        if (token == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Authorization", token);

        return ResponseEntity.ok().headers(responseHeader).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(required = false) String name) {
        List<UserDTO> list = service.getUsers(name);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO user) {

        int result = service.signup(user);

        if (result == 1) {
            return ResponseEntity.badRequest().body("Nome de usuario invalido!");
        } else if (result == 2) {
            return ResponseEntity.badRequest().body("E-mail de usuario invalido!");
        } else if (result == 3) {
            return ResponseEntity.badRequest().body("Senha deve ter mais que 6 caracters.");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgotpass")
    public ResponseEntity<String> forgotPassword(@RequestBody UserDTO user) {

        int result = service.forgotPassword(user);

        if (result == 1) {
            return ResponseEntity.badRequest().body("E-mail de usuario invalido!");
        }
        else if (result == 2) {
            return ResponseEntity.badRequest().body("Falha ao enviar email!");
        }

        return ResponseEntity.ok().build();
    }

}
