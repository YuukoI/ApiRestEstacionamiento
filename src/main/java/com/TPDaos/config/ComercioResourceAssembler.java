package com.TPDaos.config;

import com.TPDaos.rest.ComercioController;
import com.TPDaos.domain.Comercio;
import com.TPDaos.dto.ComercioDTO;
import com.TPDaos.rest.RecargaController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComercioResourceAssembler {

    public EntityModel<ComercioDTO> toModel(Comercio comercio) {
        ComercioDTO comercioDTO = new ComercioDTO();
        comercioDTO.setCuit(comercio.getCuit());
        comercioDTO.setRazonSocial(comercio.getRazonSocial());
        comercioDTO.setDireccion(comercio.getDireccion());

        EntityModel<ComercioDTO> comercioModel = EntityModel.of(comercioDTO);

        comercioModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComercioController.class)
                .getById(comercio.getCuit())).withSelfRel());

        comercioModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecargaController.class)
                .mostrarRecargaPorNumeroComercio(comercio.getCuit())).withRel("recarga"));

        return comercioModel;
    }

    public CollectionModel<EntityModel<ComercioDTO>> toCollectionModel(List<Comercio> comercios) {
        List<EntityModel<ComercioDTO>> comercioModels = comercios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(comercioModels);
    }
}
