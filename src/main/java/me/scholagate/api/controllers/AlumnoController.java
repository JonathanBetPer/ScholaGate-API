package me.scholagate.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Tag(name = "Alumnos Controller", description = "Controlador de Alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

}
