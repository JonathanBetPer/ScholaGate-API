package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.services.AdjuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: Por Realizar
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Adjunto Controller", description = "Controlador de Adjuntos")
public class AdjuntoController {
    @Autowired
    private AdjuntoService adjuntoService;

    AdjuntoController(AdjuntoService adjuntoService) {
        this.adjuntoService = adjuntoService;
    }

}
