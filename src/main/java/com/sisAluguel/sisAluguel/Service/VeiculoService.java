package com.sisAluguel.sisAluguel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisAluguel.sisAluguel.Model.Veiculo;
import com.sisAluguel.sisAluguel.Repository.VeiculoRepository;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll(){
        return veiculoRepository.findAll();
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void delete(Long id) {
        veiculoRepository.deleteById(id);
    }
}
