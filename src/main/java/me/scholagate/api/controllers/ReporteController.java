package me.scholagate.api.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Tag(name = "Reportes Controller", description = "Controlador de Reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

}
