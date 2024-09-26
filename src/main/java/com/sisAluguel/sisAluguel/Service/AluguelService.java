package com.sisAluguel.sisAluguel.Service;

import java.util.List;

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

    public List<Aluguel> findAll() {
        return aluguelRepository.findAll();
    }

    public Aluguel findById(Long idAluguel) {
        return aluguelRepository.findById(idAluguel).orElse(null);
    }

    public Aluguel save(Aluguel aluguel) {
        // Recupera os objetos Usuario e Veiculo com base nos IDs
        Usuario usuario = usuarioRepository.findById(aluguel.getUsuario().getId())
                             .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Veiculo veiculo = veiculoRepository.findById(aluguel.getVeiculo().getId())
                             .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // Atribui os objetos completos ao aluguel
        aluguel.setUsuario(usuario);
        aluguel.setVeiculo(veiculo);

        // Salva o aluguel no banco de dados
        return aluguelRepository.save(aluguel);
    }

    public void delete(Long idAluguel) {
        aluguelRepository.deleteById(idAluguel);
    }
}
