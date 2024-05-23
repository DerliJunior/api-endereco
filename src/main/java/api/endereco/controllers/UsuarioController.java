package api.endereco.controllers;

import api.endereco.Dtos.NewUsuarioDTO;
import api.endereco.Dtos.NewUsuarioResponseDTO;
import api.endereco.models.Usuario;
import api.endereco.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> listaUsuarios = this.usuarioService.findAll();

        return ResponseEntity.ok(listaUsuarios);
    }

    @PostMapping("/criar")
    public ResponseEntity<NewUsuarioResponseDTO> criar(@RequestBody NewUsuarioDTO usuarioDTO) {
        NewUsuarioResponseDTO usuario = this.usuarioService.cadastrarNovoUsuario(usuarioDTO);

        return ResponseEntity.created(null).body(usuario);
    }
}
