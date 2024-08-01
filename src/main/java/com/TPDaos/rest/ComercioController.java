package com.TPDaos.rest;

import com.TPDaos.domain.Comercio;
import com.TPDaos.dto.ComercioDTO;
import com.TPDaos.service.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comercio")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    @GetMapping
    public List<Comercio> getAll() {
        return comercioService.obtenerComercios();
    }

    @GetMapping("/{cuit}")
    public ResponseEntity<Comercio> getById(@PathVariable Long cuit) {
        Comercio comercioExistente = comercioService.obtenerComercio(cuit);

        if (comercioExistente != null) {
            return new ResponseEntity<>(comercioExistente, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{cuit}")
    public ResponseEntity<Comercio> deleteComercio(@PathVariable Long cuit) {
        Comercio comercioExistente = comercioService.obtenerComercio(cuit);

        if (comercioExistente != null) {
            comercioService.eliminarComercio(cuit);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Comercio> guardarComercio(@RequestBody ComercioDTO comercioDTO) {
        Comercio comercio = comercioService.guardarComercio(comercioDTO);
        return new ResponseEntity<>(comercio, HttpStatus.CREATED);
    }

    @PutMapping("/{cuit}")
    public ResponseEntity<Comercio> actualizarComercio(@PathVariable Long cuit, @RequestBody ComercioDTO comercioDTO){
        Comercio comercio = comercioService.actualizarComercio(cuit, comercioDTO);
        return new ResponseEntity<>(comercio, HttpStatus.OK);
    }
}
