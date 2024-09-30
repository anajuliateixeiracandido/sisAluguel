package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Service.UsuarioService;
import com.sisAluguel.sisAluguel.Service.AluguelService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AluguelService aluguelService;

    @GetMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView("login_form");
    }

    @PostMapping("/login")
    public ModelAndView login(Usuario usuario, HttpSession session) {
        Usuario usuarioEncontrado = usuarioService.findbyCredentials(usuario.getCpf(), usuario.getSenha());

        ModelAndView mv = new ModelAndView();

        if (usuarioEncontrado != null) {
            session.setAttribute("usuarioLogadoId", usuarioEncontrado.getId());
            session.setAttribute("usuarioNome", usuarioEncontrado.getNome()); // Adiciona o nome à sessão

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
    public ModelAndView dashboardCliente(HttpSession session) {
        ModelAndView mv = new ModelAndView("dashboard_cliente");

        // Pega o nome do usuário logado
        String nomeUsuario = (String) session.getAttribute("usuarioNome");
        mv.addObject("nomeUsuario", nomeUsuario); // Passa o nome do usuário para a página

        // Pega o ID do usuário logado
        Long usuarioLogadoId = (Long) session.getAttribute("usuarioLogadoId");

        // Busque os alugueis do usuário através do seu serviço de aluguel
        List<Aluguel> alugueis = aluguelService.findByUsuarioId(usuarioLogadoId);
        mv.addObject("alugueis", alugueis); // Passa a lista de aluguéis para a página

        return mv;
    }

    @GetMapping("/dashboard_agente")
    public ModelAndView dashboardAgente(HttpSession session) {
        ModelAndView mv = new ModelAndView("dashboard_agente");

        String nomeUsuario = (String) session.getAttribute("usuarioNome");
        mv.addObject("nomeUsuario", nomeUsuario);

        List<Aluguel> alugueis = aluguelService.findAll();
        mv.addObject("alugueis", alugueis);

        return mv;
    }

}
