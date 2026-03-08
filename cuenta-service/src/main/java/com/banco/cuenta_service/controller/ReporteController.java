package com.banco.cuenta_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.banco.cuenta_service.dto.ReporteDTO;
import com.banco.cuenta_service.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<ReporteDTO> reporte(
        @RequestParam 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate inicio,

        @RequestParam 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fin){

        return reporteService.generarReporte(inicio, fin);
    }
}