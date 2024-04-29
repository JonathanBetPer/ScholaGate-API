package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.services.CursoServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Tag(name = "Cursos Controller", description = "Controlador de Cursos")
public class CursoController {
    @Autowired
    private CursoServive cursoService;

    CursoController(CursoServive cursoService) {
        this.cursoService = cursoService;
    }

}
