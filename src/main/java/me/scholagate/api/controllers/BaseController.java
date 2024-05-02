package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Base Controller", description = "Controlador Base")
public class BaseController {
    @GetMapping("/")
    public String index() {
        return "Welcome to Scholagate API";
    }
}
