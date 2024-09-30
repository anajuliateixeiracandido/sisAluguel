package com.sisAluguel.sisAluguel.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Model.Veiculo;
import com.sisAluguel.sisAluguel.Repository.AluguelRepository;
import com.sisAluguel.sisAluguel.Repository.UsuarioRepository;
import com.sisAluguel.sisAluguel.Repository.VeiculoRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Recupera todos os aluguéis
    public List<Aluguel> findAll() {
        return aluguelRepository.findAll();
    }

    // Busca um aluguel por ID
    public Aluguel findById(Long idAluguel) {
        return aluguelRepository.findById(idAluguel).orElse(null);
    }

    // Salva ou atualiza um aluguel
    public Aluguel save(Aluguel aluguel) {
        // Recupera os objetos Usuario e Veiculo com base nos IDs
        Usuario usuario = usuarioRepository.findById(aluguel.getUsuario().getId())
                             .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Veiculo veiculo = veiculoRepository.findById(aluguel.getVeiculo().getIdVeiculo())
                             .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // Atribui os objetos completos ao aluguel
        aluguel.setUsuario(usuario);
        aluguel.setVeiculo(veiculo);

        // Salva o aluguel no banco de dados
        return aluguelRepository.save(aluguel);
    }

    // Deleta um aluguel pelo ID
    public void delete(Long idAluguel) {
        aluguelRepository.deleteById(idAluguel);
    }

    // Busca aluguéis por ID do usuário
    public List<Aluguel> findByUsuarioId(Long usuarioId) {
        return aluguelRepository.findByUsuarioId(usuarioId);
    }

    // Busca aluguéis por status
    public List<Aluguel> findByStatus(Aluguel.Status status) {
    return aluguelRepository.findByStatus(status); // Converte o enum para String
}


    // Busca aluguéis com status PENDENTE
    public List<Aluguel> findAlugueisPendentes() {
        return aluguelRepository.findByStatus(Aluguel.Status.PENDENTE);
    }

    // Atualiza o status de um aluguel
    public Aluguel updateStatus(Long idAluguel, Aluguel.Status status) {
        Optional<Aluguel> optionalAluguel = aluguelRepository.findById(idAluguel);
        if (optionalAluguel.isPresent()) {
            Aluguel aluguel = optionalAluguel.get();
            aluguel.setStatus(status);
            return aluguelRepository.save(aluguel);
        } else {
            throw new RuntimeException("Aluguel não encontrado");
        }
    }
}
