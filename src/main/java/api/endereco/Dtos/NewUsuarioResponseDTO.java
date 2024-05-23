package api.endereco.Dtos;

import api.endereco.models.Endereco;
import api.endereco.models.Usuario;
import lombok.Getter;

@Getter
public class NewUsuarioResponseDTO{
    private Long id;
    private String nome;
    private Endereco endereco;

    public NewUsuarioResponseDTO(Usuario usuario, Endereco endereco) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.endereco = endereco;
    }
}
