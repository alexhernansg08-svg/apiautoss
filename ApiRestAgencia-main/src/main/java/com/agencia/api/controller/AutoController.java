package com.agencia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agencia.api.entities.Auto;
import com.agencia.api.repository.AutoRepository;

import java.util.List;

@RestController
// Ya quitamos el @RequestMapping("/api")
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    // ... (Métodos POST, DELETE, y /marca/ y /precio/mayor/ permanecen igual) ...

    // ============================================================
    // NUEVO 1: BUSCAR AUTOS POR MODELO EXACTO (USANDO @RequestParam)
    // URL: /api/busquedas/modelo-exacto?modelo=2024
    // ============================================================
    @GetMapping("/api/busquedas/modelo-exacto")
    public ResponseEntity<List<Auto>> getAutosByModelo(@RequestParam Integer modelo) {
        List<Auto> autos = autoRepository.findByModelo(modelo);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }

    // ============================================================
    // NUEVO 2: BUSCAR AUTOS DONDE MODELO SEA MENOR A X (USANDO @RequestParam)
    // URL: /api/busquedas/modelo-menor?anio=2025
    // ============================================================
    @GetMapping("/api/busquedas/modelo-menor")
    public ResponseEntity<List<Auto>> getAutosByModeloMenorA(@RequestParam Integer anio) {
        List<Auto> autos = autoRepository.findByModeloLessThan(anio);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }

    // ============================================================
    // NUEVO 3: BUSCAR AUTOS POR PAÍS DE LA MARCA (USANDO @RequestParam)
    // URL: /api/busquedas/pais?pais=EEUU
    // ============================================================
    @GetMapping("/api/busquedas/pais")
    public ResponseEntity<List<Auto>> getAutosByMarcaPais(@RequestParam String pais) {
        List<Auto> autos = autoRepository.findAutosByMarcaPais(pais);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }
}