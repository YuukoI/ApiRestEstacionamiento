package com.TPDaos.config;

import com.TPDaos.domain.Estacionamiento;
import com.TPDaos.rest.UsuarioController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class EstacionamientoResourceAssembler implements RepresentationModelAssembler<Estacionamiento, EntityModel<Estacionamiento>> {

    @Override
    public EntityModel<Estacionamiento> toModel(Estacionamiento estacionamiento) {

        EntityModel<Estacionamiento> estacionamientoModel = EntityModel.of(estacionamiento);

        Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscarUsuarioPorDNI(estacionamiento.getDniUsuario())).withRel("usuario");

        estacionamientoModel.add(userLink);

        return estacionamientoModel;
    }
}
