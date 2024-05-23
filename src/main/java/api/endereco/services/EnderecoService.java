package api.endereco.services;

import api.endereco.models.Endereco;
import api.endereco.repositories.IEnderecoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    IEnderecoRepository enderecoRepository;

    public EnderecoService(IEnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void salvarEndereco(Endereco endereco) {
        this.enderecoRepository.save(endereco);
    }

    public Endereco buscarEnderecoPeloId(Long id) {
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatusCode.valueOf(404), "Endereço não encontrado");
        }

        return endereco.get();
    }

    public void atualizarEndereco(Endereco endereco) {
        verificarEndereco(endereco.getId());
        this.enderecoRepository.save(endereco);
    }

    public void excluirEndereco(Long id) {
        verificarEndereco(id);
        this.enderecoRepository.deleteById(id);
    }

    public List<Endereco> listarEnderecos() {
        List<Endereco> listaEnderecos =  this.enderecoRepository.findAll();

        return listaEnderecos;
    }

    public void verificarEndereco(Long id) {
        Boolean isEnderecoValido = this.enderecoRepository.existsById(id);

        if (!isEnderecoValido) {
            throw new ResponseStatusException(
                    HttpStatusCode.valueOf(404), "Endereço inexistente");
        }
    }

}
