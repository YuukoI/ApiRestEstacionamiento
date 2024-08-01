package com.TPDaos.rest;

import com.TPDaos.config.EstacionamientoResourceAssembler;
import com.TPDaos.domain.Estacionamiento;
import com.TPDaos.dto.EstacionamientoDTO;
import com.TPDaos.service.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estacionamiento")
public class EstacionamientoController {

    @Autowired
    private EstacionamientoService estacionamientoService;

    private final EstacionamientoResourceAssembler estacionamientoAssembler;

    public EstacionamientoController(EstacionamientoService estacionamientoService, EstacionamientoResourceAssembler assembler) {
        this.estacionamientoService = estacionamientoService;
        this.estacionamientoAssembler = assembler;
    }

    @GetMapping
    public PagedModel<EntityModel<Estacionamiento>> getEstacionamientos() {
        List<Estacionamiento> estacionamientos = estacionamientoService.getEstacionamientos();
        List<EntityModel<Estacionamiento>> resources = estacionamientos.stream()
                .map(estacionamientoAssembler::toModel)
                .collect(Collectors.toList());
        return PagedModel.of(resources, new PagedModel.PageMetadata(resources.size(), 0, resources.size()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Estacionamiento>> getEstacionamiento(@PathVariable Long id) {
        Estacionamiento estacionamiento = estacionamientoService.getEstacionamientoById(id);

        if (estacionamiento != null) {
            return ResponseEntity.ok(estacionamientoAssembler.toModel(estacionamiento));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estacionamiento> deleteEstacionamiento(@PathVariable Long id) {
        Estacionamiento estacionamiento = estacionamientoService.getEstacionamientoById(id);

        if(estacionamiento != null) {
            estacionamientoService.deleteEstacionamiento(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<Estacionamiento> addEstacionamiento(@RequestBody EstacionamientoDTO estacionamientoDTO) {
        estacionamientoService.addEstacionamiento(estacionamientoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estacionamiento> updateEstacionamiento(@PathVariable Long id, @RequestBody EstacionamientoDTO estacionamientoDTO) {
        estacionamientoService.updateEstacionamiento(estacionamientoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
