package com.TPDaos.rest;

import com.TPDaos.config.UsuarioResourceAssembler;
import com.TPDaos.domain.Usuario;
import com.TPDaos.service.PatenteService;
import com.TPDaos.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PatenteService patenteService;

    private UsuarioResourceAssembler usuarioResourceAssembler;

    public UsuarioController(UsuarioService usuarioService, UsuarioResourceAssembler assembler) {
        this.usuarioService = usuarioService;
        this.usuarioResourceAssembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();

        List<EntityModel<Usuario>> usuarioResources = usuarios.stream()
                .map(usuarioResourceAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Usuario>> collectionModel = CollectionModel.of(usuarioResources);
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listarUsuarios()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{DNI}")
    public ResponseEntity<EntityModel<Usuario>> buscarUsuarioPorDNI(@PathVariable String DNI) {
        Usuario usuario = usuarioService.getUsuario(DNI);
        if (usuario != null) {
            EntityModel<Usuario> usuarioResource = usuarioResourceAssembler.toModel(usuario);
            return new ResponseEntity<>(usuarioResource, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<Usuario> insertarUsuario(@RequestBody Usuario usuario) {
        usuarioService.addUsuario(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{DNI}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable String DNI) {
        Usuario usuarioViejo = usuarioService.getUsuario(DNI);
        if (usuarioViejo != null) {
            usuarioService.deleteUsuario(DNI);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{DNI}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable String DNI, @RequestBody Usuario usuario) {
        Usuario usuarioViejo = usuarioService.getUsuario(DNI);
        if (usuario != null) {
            usuario.setDni(DNI);
            usuarioService.updateUsuario(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

