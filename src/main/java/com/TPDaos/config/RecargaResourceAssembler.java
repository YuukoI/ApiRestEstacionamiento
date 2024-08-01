package com.TPDaos.config;

import com.TPDaos.rest.UsuarioController;
import com.TPDaos.rest.ComercioController;
import com.TPDaos.rest.RecargaController;
import com.TPDaos.domain.Recarga;
import com.TPDaos.service.UsuarioService;
import com.TPDaos.service.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class RecargaResourceAssembler {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ComercioService comercioService;

    public EntityModel<Recarga> toModel(Recarga recarga) {
        EntityModel<Recarga> recargaModel = EntityModel.of(recarga);

        Link usuarioLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .buscarUsuarioPorDNI(recarga.getUsuario().getDni())).withRel("usuario");
        Link comercioLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComercioController.class)
                .getById(recarga.getComercio().getCuit())).withRel("comercio");

        recargaModel.add(usuarioLink);
        recargaModel.add(comercioLink);

        return recargaModel;
    }

    public CollectionModel<EntityModel<Recarga>> toCollectionModel(Iterable<? extends Recarga> recargas) {
        CollectionModel<EntityModel<Recarga>> recargaCollectionModel = CollectionModel.of(
                StreamSupport.stream(recargas.spliterator(), false)
                        .map(this::toModel)
                        .collect(Collectors.toList())
        );

        recargaCollectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecargaController.class).mostrarRecargas()).withSelfRel());

        return recargaCollectionModel;
    }
}