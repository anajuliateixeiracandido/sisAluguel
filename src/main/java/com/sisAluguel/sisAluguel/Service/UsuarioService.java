package com.sisAluguel.sisAluguel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Model.Endereco;
import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Repository.EnderecoRepository;
import com.sisAluguel.sisAluguel.Repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;  // Injeta o repositório de endereço

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
     public Usuario save(Usuario usuario) {
        if (usuario.getEndereco() != null) {
            Endereco savedEndereco = enderecoRepository.save(usuario.getEndereco());
            usuario.setEndereco(savedEndereco);  
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioAtualizado) {
        // Verificar se o usuário já existe
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualizar os campos do usuário existente com os dados fornecidos
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setRg(usuarioAtualizado.getRg());
        usuarioExistente.setProfissao(usuarioAtualizado.getProfissao());
        usuarioExistente.setEntidadeEmpregadora(usuarioAtualizado.getEntidadeEmpregadora());
        usuarioExistente.setRendimentoAuferido(usuarioAtualizado.getRendimentoAuferido());
        usuarioExistente.setTipo(usuarioAtualizado.getTipo());

        // Atualizar o endereço, se presente
        if (usuarioAtualizado.getEndereco() != null) {
            Endereco savedEndereco = enderecoRepository.save(usuarioAtualizado.getEndereco());
            usuarioExistente.setEndereco(savedEndereco);
        }

        return usuarioRepository.save(usuarioExistente);
    }

   
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Adicione este método na classe UsuarioService
public Usuario findByCpf(String cpf) {
    return usuarioRepository.findByCpf(cpf);
}

public Usuario findbyCredentials(String cpf, String senha) {
    return usuarioRepository.findByCpfAndSenha(cpf, senha);
}


public List<Aluguel> findAlugueisByCpf(String cpf) {
    Usuario usuario = usuarioRepository.findByCpf(cpf);
    if (usuario != null) {
        return usuario.getAlugueis();
    }
    return new ArrayList<>(); // Retorna uma lista vazia se o usuário não tiver aluguéis ou não for encontrado
}



}
