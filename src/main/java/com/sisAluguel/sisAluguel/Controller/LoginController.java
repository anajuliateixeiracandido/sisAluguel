package com.sisAluguel.sisAluguel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView("login_form");
    }
    

    @PostMapping("/login")
    public ModelAndView login(Usuario usuario, HttpSession session) {
        Usuario usuarioEncontrado = usuarioService.findByCredentials(usuario.getCpf(), usuario.getSenha());

        ModelAndView mv = new ModelAndView();

        if (usuarioEncontrado != null) {
            session.setAttribute("usuarioLogadoId", usuarioEncontrado.getId());

            if ("cliente".equals(usuarioEncontrado.getTipo())) {
                mv.setViewName("redirect:/dashboard_cliente");
            } else if ("agente".equals(usuarioEncontrado.getTipo())) {
                mv.setViewName("redirect:/dashboard_agente");
            }
        } else {
            mv.setViewName("login_form");
            mv.addObject("erro", "Credenciais inválidas.");
        }

        return mv;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Limpa a sessão do usuário
        return "redirect:/login?logout"; // Redireciona para a página de login
    }

    @GetMapping("/dashboard_cliente")
    public String dashboardCliente() {
        return "dashboard_cliente"; // Deve corresponder ao arquivo HTML
    }

    @GetMapping("/dashboard_agente")
    public String dashboardAgente() {
        return "dashboard_agente"; // Deve corresponder ao arquivo HTML
    }

    
}
