package api.endereco.services;

import api.endereco.Dtos.NewUsuarioDTO;
import api.endereco.Dtos.NewUsuarioResponseDTO;
import api.endereco.models.Endereco;
import api.endereco.models.Usuario;
import api.endereco.repositories.IEnderecoRepository;
import api.endereco.repositories.IUsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    IUsuarioRepository usuarioRepository;
    IEnderecoRepository enderecoRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository,
                          IEnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public NewUsuarioResponseDTO cadastrarNovoUsuario(NewUsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.nome());

        Usuario usuarioGerado = this.usuarioRepository.save(usuario);

        Endereco endereco = new Endereco();

        endereco.setRua(usuarioDTO.rua());
        endereco.setNumero(usuarioDTO.numero());
        endereco.setCep(usuarioDTO.cep());
        endereco.setUsuario(usuarioGerado);

        Endereco enderecoGerado = this.enderecoRepository.save(endereco);

        return new NewUsuarioResponseDTO(usuarioGerado, enderecoGerado);
    }
}
