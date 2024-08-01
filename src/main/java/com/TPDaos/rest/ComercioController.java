package com.TPDaos.rest;

import com.TPDaos.config.ComercioResourceAssembler;
import com.TPDaos.domain.Comercio;
import com.TPDaos.dto.ComercioDTO;
import com.TPDaos.service.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comercio")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    @Autowired
    private ComercioResourceAssembler comercioResourceAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ComercioDTO>>> getAll() {
        List<Comercio> comercios = comercioService.obtenerComercios();
        CollectionModel<EntityModel<ComercioDTO>> comercioCollectionModel = comercioResourceAssembler.toCollectionModel(comercios);
        return new ResponseEntity<>(comercioCollectionModel, HttpStatus.OK);
    }

    @GetMapping("/{cuit}")
    public ResponseEntity<EntityModel<ComercioDTO>> getById(@PathVariable Long cuit) {
        Comercio comercio = comercioService.obtenerComercio(cuit);
        if (comercio != null) {
            EntityModel<ComercioDTO> comercioModel = comercioResourceAssembler.toModel(comercio);
            return new ResponseEntity<>(comercioModel, HttpStatus.OK);
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
