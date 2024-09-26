package com.sisAluguel.sisAluguel.Bean;

import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L; // Adicione um serialVersionUID para a serialização

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;

    @Autowired
    private UsuarioService usuarioService;

    // Método para listar os usuários
    public void listar() {
        usuarios = usuarioService.findAll();
    }

    // Método para salvar o usuário
    public void salvar() {
        usuarioService.save(usuario);
        listar();  // Atualiza a lista após salvar
        usuario = new Usuario(); // Reseta o objeto usuário após salvar
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @PostConstruct
public void init() {
    listar(); // Chama listar após a construção
}
}
