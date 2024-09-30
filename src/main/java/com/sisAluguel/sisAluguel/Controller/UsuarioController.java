package com.sisAluguel.sisAluguel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Método para obter todos os usuários (página de listagem)
    @GetMapping("/")
    public ModelAndView getAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        ModelAndView mv = new ModelAndView("usuario_list");
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    // Método para exibir o formulário de criação de novo usuário
    @GetMapping("/novo")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("usuario_form");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    // Método para salvar um novo usuário
    @PostMapping("/novo")
    public ModelAndView create(Usuario usuario) {
        usuarioService.save(usuario);
        return new ModelAndView("redirect:/login");
    }

    // Método para exibir o perfil do usuário logado
    @GetMapping("/perfil")
    public ModelAndView exibirPerfil(HttpSession session) {
        Long usuarioLogadoId = (Long) session.getAttribute("usuarioLogadoId");
        
        ModelAndView mv = new ModelAndView("usuario_edit");
        
        if (usuarioLogadoId != null) {
            Usuario usuario = usuarioService.findById(usuarioLogadoId);
            mv.addObject("usuario", usuario);
        } else {
            mv.setViewName("redirect:/login");  // Se o usuário não estiver logado, redireciona para login
        }

        return mv;
    }

    @PostMapping("/perfil")
public ModelAndView atualizarPerfil(@ModelAttribute("usuario") Usuario usuario, HttpSession session) {
    Long usuarioLogadoId = (Long) session.getAttribute("usuarioLogadoId");
    ModelAndView mv = new ModelAndView("usuario_edit");

    if (usuarioLogadoId != null) {
        try {
            // Encontre o usuário atual
            Usuario usuarioAtual = usuarioService.findById(usuarioLogadoId);
            
            if (usuarioAtual != null) {
                usuario.setId(usuarioLogadoId);  // Certifique-se de que o ID não seja alterado
                
                // Mantenha o tipo do usuário atual
                usuario.setTipo(usuarioAtual.getTipo());

                // Log dos dados do usuário e do endereço antes de salvar
                System.out.println("Usuario antes de salvar: " + usuario);
                System.out.println("Endereco antes de salvar: " + usuario.getEndereco());

                // Tenta salvar as alterações
                usuarioService.save(usuario); // Salva o usuário e o endereço
                
                mv.addObject("successMessage", "Perfil atualizado com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Para debug: veja o erro no console
            mv.addObject("errorMessage", "Erro ao atualizar perfil: " + e.getMessage());
        }
    } else {
        mv.setViewName("redirect:/login");  // Se o usuário não estiver logado, redireciona para login
    }

    // Adiciona o usuário atualizado ao modelo para exibição
    mv.addObject("usuario", usuarioService.findById(usuarioLogadoId));
    return mv;  // Retorna à mesma página de perfil
}


}
