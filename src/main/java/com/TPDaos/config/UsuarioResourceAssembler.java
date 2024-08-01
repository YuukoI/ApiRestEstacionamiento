package com.TPDaos.config;

import com.TPDaos.dao.EstacionamientoRepository;
import com.TPDaos.domain.Estacionamiento;
import com.TPDaos.domain.Usuario;
import com.TPDaos.rest.EstacionamientoController;
import com.TPDaos.rest.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResourceAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    private final EstacionamientoRepository estacionamientoRepository;

    public UsuarioResourceAssembler(EstacionamientoRepository estacionamientoRepository) {
        this.estacionamientoRepository = estacionamientoRepository;
    }

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        EntityModel<Usuario> resource = EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscarUsuarioPorDNI(usuario.getDni())).withSelfRel()
        );

        if (usuario.getPatente() != null) {
            Estacionamiento estacionamiento = estacionamientoRepository.findEstacionamientoByPatente(usuario.getPatente().getNumero());

            if (estacionamiento != null) {
                Long estacionamientoId = estacionamiento.getId();
                resource.add(
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstacionamientoController.class).getEstacionamiento(estacionamientoId)).withRel("estadoEstacionamiento")
                );
            }
        }

        return resource;
    }
}