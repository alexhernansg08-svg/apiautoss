package com.agencia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agencia.api.entities.Auto;
import com.agencia.api.repository.AutoRepository;

import java.util.List;

@RestController
// REMOVIDO: @RequestMapping("/api")
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    // ============================================================
    //  DELETE AUTO POR NO_SERIE
    //  AHORA ES: /api/auto/{noSerie}
    // ============================================================

    @DeleteMapping("/api/auto/{noSerie}")
    public ResponseEntity<Void> deleteAuto(@PathVariable String noSerie) {
        if (!autoRepository.existsById(noSerie)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        autoRepository.deleteById(noSerie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ============================================================
    //  CREAR AUTO
    //  AHORA ES: /api/auto
    // ============================================================

    @PostMapping("/api/auto")
    public ResponseEntity<Auto> createAuto(@RequestBody Auto auto) {
        try {
            Auto nuevoAuto = autoRepository.save(auto);
            return new ResponseEntity<>(nuevoAuto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // ============================================================
    //  BUSCAR AUTOS POR NOMBRE DE MARCA
    //  AHORA ES: /api/autos/marca/{nombre}
    // ============================================================

    @GetMapping("/api/autos/marca/{nombre}")
    public ResponseEntity<List<Auto>> getAutosByMarca(@PathVariable String nombre) {
        List<Auto> autos = autoRepository.findAutosByMarcaNombre(nombre);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }

    // ============================================================
    //  BUSCAR AUTOS POR PRECIO MAYOR A
    //  AHORA ES: /api/autos/precio/mayor/{precio}
    // ============================================================

    @GetMapping("/api/autos/precio/mayor/{precio}")
    public ResponseEntity<List<Auto>> getAutosByPrecioMayorA(@PathVariable Double precio) {
        List<Auto> autos = autoRepository.findAutosByPrecioMayorA(precio);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }

    // ============================================================
    //  NUEVO 1: BUSCAR AUTOS POR MODELO EXACTO (RUTA CORREGIDA)
    //  AHORA ES: /api/autos/modelo-exacto/2024
    // ============================================================

    @GetMapping("/api/autos/modelo-exacto/{modelo}")
    public ResponseEntity<List<Auto>> getAutosByModelo(@PathVariable Integer modelo) {
        List<Auto> autos = autoRepository.findByModelo(modelo);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }

    // ============================================================
    //  NUEVO 2: BUSCAR AUTOS DONDE MODELO SEA MENOR A X (RUTA CORREGIDA)
    //  AHORA ES: /api/autos/modelo-menor/2025
    // ============================================================

    @GetMapping("/api/autos/modelo-menor/{anio}")
    public ResponseEntity<List<Auto>> getAutosByModeloMenorA(@PathVariable Integer anio) {
        List<Auto> autos = autoRepository.findByModeloLessThan(anio);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }

    // ============================================================
    //  NUEVO 3: BUSCAR AUTOS POR PAÍS DE LA MARCA
    //  AHORA ES: /api/autos/pais/EEUU
    // ============================================================

    @GetMapping("/api/autos/pais/{pais}")
    public ResponseEntity<List<Auto>> getAutosByMarcaPais(@PathVariable String pais) {
        List<Auto> autos = autoRepository.findAutosByMarcaPais(pais);
        return autos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(autos, HttpStatus.OK);
    }
}