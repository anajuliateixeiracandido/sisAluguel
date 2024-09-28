package com.sisAluguel.sisAluguel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisAluguel.sisAluguel.Model.Endereco;
import com.sisAluguel.sisAluguel.Repository.EnderecoRepository;


@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long idEndereco) {
        return enderecoRepository.findById(idEndereco).orElse(null);
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void delete(Long idEndereco) {
        enderecoRepository.deleteById(idEndereco);
    }
    
}
