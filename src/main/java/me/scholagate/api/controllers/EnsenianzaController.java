package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.services.EnsenianzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ensenianza")
@Tag(name = "Enseñanzas Controller", description = "Controlador de Enseñanzas")
public class EnsenianzaController extends BaseController {
    @Autowired
    private EnsenianzaService ensenianzaService;

    EnsenianzaController(EnsenianzaService ensenianzaService) {
        this.ensenianzaService = ensenianzaService;
    }
}
