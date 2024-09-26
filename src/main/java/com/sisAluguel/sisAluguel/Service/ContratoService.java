package com.sisAluguel.sisAluguel.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Model.Contrato;
import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Repository.AluguelRepository;
import com.sisAluguel.sisAluguel.Repository.ContratoRepository;
import com.sisAluguel.sisAluguel.Repository.UsuarioRepository;

@Service
public class ContratoService {
    
    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Contrato> findAll() {
        return contratoRepository.findAll();
    }

    public Contrato findById(Long idContrato) {
        return contratoRepository.findById(idContrato).orElse(null);
    }

    public Contrato save(Contrato contrato) {
        if (contrato.getAluguel() != null && contrato.getAluguel().getIdAluguel() != null) {
            Aluguel aluguel = aluguelRepository.findById(contrato.getAluguel().getIdAluguel())
                             .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
            contrato.setAluguel(aluguel);  
        } else {
            throw new IllegalArgumentException("O ID do aluguel é necessário.");
        }

        if (contrato.getUsuario() != null && contrato.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(contrato.getUsuario().getId())
                             .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            contrato.setUsuario(usuario);  
        } else {
            throw new IllegalArgumentException("O ID do usuário é necessário.");
        }
    
     
        return contratoRepository.save(contrato);
    }
    
    
    
    
    


    public void delete(Long idContrato) {
        contratoRepository.deleteById(idContrato);
    }
    
}
