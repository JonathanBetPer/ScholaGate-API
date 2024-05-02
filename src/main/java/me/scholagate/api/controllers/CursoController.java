package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
@Tag(name = "Cursos Controller", description = "Controlador de Cursos")
public class CursoController extends BaseController {
    @Autowired
    private CursoService cursoService;

    CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

}
