package com.sisAluguel.sisAluguel.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sisAluguel.sisAluguel.Model.Endereco;
import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Repository.EnderecoRepository;
import com.sisAluguel.sisAluguel.Repository.UsuarioRepository;

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

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
