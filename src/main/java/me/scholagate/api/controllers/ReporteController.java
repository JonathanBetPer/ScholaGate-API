package me.scholagate.api.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import me.scholagate.api.models.Reporte;
import me.scholagate.api.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reporte")
@Tag(name = "Reportes Controller", description = "Controlador de Reportes")
public class ReporteController extends BaseController {

    @Autowired
    private ReporteService reporteService;

    ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping()
    public ResponseEntity<List<Reporte>> getReportes() {

        List<Reporte> reportes = reporteService.findAll();

        if (reportes == null || reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reportes);
    }

}
