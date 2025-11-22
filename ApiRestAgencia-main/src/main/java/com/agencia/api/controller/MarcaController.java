package com.agencia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.api.entities.Marca;
import com.agencia.api.repository.MarcaRepository;

import java.util.List;

@RestController
// REMOVIDO: @RequestMapping("/api")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    /**
     *GET http://localhost:8080/api/marcas
     * RUTA COMPLETA: /api/marcas
     */
    @GetMapping("/api/marcas")
    public ResponseEntity<List<Marca>> getMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }
}