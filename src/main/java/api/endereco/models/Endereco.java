package api.endereco.models;

import api.endereco.Dtos.NewUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Endereco {

    //Getter & Setter
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "Rua")
    private String rua;

    @Column(name = "Número")
    private int numero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Endereco(NewUsuarioDTO usuarioDTO, Long id) {
        this.cep = usuarioDTO.cep();
        this.rua = usuarioDTO.rua();
        this.numero = usuarioDTO.numero();

    }

    public Long getUsuario() {
        return usuario.getId();
    }


}
