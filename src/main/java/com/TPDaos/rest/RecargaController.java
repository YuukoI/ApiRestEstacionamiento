package com.TPDaos.rest;

import com.TPDaos.config.RecargaResourceAssembler;
import com.TPDaos.domain.Recarga;
import com.TPDaos.dto.RecargaDTO;
import com.TPDaos.service.RecargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recarga")
public class RecargaController {

    @Autowired
    private RecargaService recargaService;

    @Autowired
    private RecargaResourceAssembler recargaResourceAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Recarga>>> mostrarRecargas() {
        List<Recarga> recargas = recargaService.getRecargas();
        CollectionModel<EntityModel<Recarga>> recargaCollectionModel = recargaResourceAssembler.toCollectionModel(recargas);
        return new ResponseEntity<>(recargaCollectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Recarga>> mostrarRecargaPorId(@PathVariable Long id) {
        Recarga recarga = recargaService.getRecarga(id);
        if (recarga != null) {
            EntityModel<Recarga> recargaModel = recargaResourceAssembler.toModel(recarga);
            return new ResponseEntity<>(recargaModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Recarga>> acreditarRecarga(@RequestBody RecargaDTO recargaDTO) {
        Recarga recarga = recargaService.addRecarga(recargaDTO);
        EntityModel<Recarga> recargaModel = recargaResourceAssembler.toModel(recarga);
        return new ResponseEntity<>(recargaModel, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{dni}")
    public ResponseEntity<CollectionModel<EntityModel<Recarga>>> mostrarRecargaPorDni(@PathVariable String dni) {
        List<Recarga> recargas = recargaService.buscarRecargaPorDni(dni);
        CollectionModel<EntityModel<Recarga>> recargaCollectionModel = recargaResourceAssembler.toCollectionModel(recargas);
        return new ResponseEntity<>(recargaCollectionModel, HttpStatus.OK);
    }

    @GetMapping("/patente/{numeroPatente}")
    public ResponseEntity<CollectionModel<EntityModel<Recarga>>> mostrarRecargaPorNumeroPatente(@PathVariable String numeroPatente) {
        List<Recarga> recargas = recargaService.buscarRecargaPorNumeroPatente(numeroPatente);
        CollectionModel<EntityModel<Recarga>> recargaCollectionModel = recargaResourceAssembler.toCollectionModel(recargas);
        return new ResponseEntity<>(recargaCollectionModel, HttpStatus.OK);
    }

    @GetMapping("/comercio/{numeroComercio}")
    public ResponseEntity<CollectionModel<EntityModel<Recarga>>> mostrarRecargaPorNumeroComercio(@PathVariable Long numeroComercio) {
        List<Recarga> recargas = recargaService.buscarRecargaPorNumeroComercio(numeroComercio);
        CollectionModel<EntityModel<Recarga>> recargaCollectionModel = recargaResourceAssembler.toCollectionModel(recargas);
        return new ResponseEntity<>(recargaCollectionModel, HttpStatus.OK);
    }
}
