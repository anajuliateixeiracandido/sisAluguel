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
        if (contrato.getAluguel() == null || contrato.getAluguel().getIdAluguel() == null) {
            throw new IllegalArgumentException("O ID do aluguel é necessário.");
        }
    
        if (contrato.getUsuario() == null || contrato.getUsuario().getId() == null) {
            throw new IllegalArgumentException("O ID do usuário é necessário.");
        }
    
        // Verificar se o aluguel e usuário existem no banco de dados
        Aluguel aluguel = aluguelRepository.findById(contrato.getAluguel().getIdAluguel())
                            .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
        Usuario usuario = usuarioRepository.findById(contrato.getUsuario().getId())
                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        // Associar os objetos ao contrato
        contrato.setAluguel(aluguel);
        contrato.setUsuario(usuario);
    
        // Se for uma edição (verificar se o ID do contrato existe)
        if (contrato.getId() != null) {
            Contrato existingContrato = contratoRepository.findById(contrato.getId())
                                    .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
            existingContrato.setValor(contrato.getValor());
            existingContrato.setMetodoPagamento(contrato.getMetodoPagamento());
            existingContrato.setNumParcela(contrato.getNumParcela());
            existingContrato.setTipo(contrato.getTipo());
            existingContrato.setData_inicio(contrato.getData_inicio());
            existingContrato.setData_fim(contrato.getData_fim());
    
            // Salvar o contrato atualizado
            return contratoRepository.save(existingContrato);
        }
    
        // Se for um novo contrato, apenas salvar
        return contratoRepository.save(contrato);
    }
    

    public void delete(Long idContrato) {
        contratoRepository.deleteById(idContrato);
    }
    
}
