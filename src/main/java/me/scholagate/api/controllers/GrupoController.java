package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: Por Realizar
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Grupos Controller", description = "Controlador de Grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }
}
