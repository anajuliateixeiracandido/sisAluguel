package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Model.Veiculo;
import com.sisAluguel.sisAluguel.Service.AluguelService;
import com.sisAluguel.sisAluguel.Service.UsuarioService;
import com.sisAluguel.sisAluguel.Service.VeiculoService;

@Controller
@RequestMapping("/aluguel")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/")
    public ModelAndView getAll() {
        List<Aluguel> alugueis = aluguelService.findAll();
        ModelAndView mv = new ModelAndView("aluguel_list");
        mv.addObject("alugueis", alugueis);
        return mv;
    }

    @GetMapping("/{idAluguel}")
    public ModelAndView getById(@PathVariable Long idAluguel) {
        Aluguel aluguel = aluguelService.findById(idAluguel);
        ModelAndView mv = new ModelAndView("aluguel_detalhes");

        if (aluguel != null) {
            mv.addObject("aluguel", aluguel);
        } else {
            mv.setViewName("not_found");
        }
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("aluguel_form");
        mv.addObject("aluguel", new Aluguel());

        List<Usuario> usuarios = usuarioService.findAll();
        List<Veiculo> veiculos = veiculoService.findAll();
        mv.addObject("usuarios", usuarios);
        mv.addObject("veiculos", veiculos);

        return mv;
    }

    @PostMapping("/novo")
public ModelAndView create(@ModelAttribute Aluguel aluguel) {
    // Definindo o status como PENDENTE
    aluguel.setStatus(Aluguel.Status.PENDENTE);
    
    // Salvando o aluguel
    aluguelService.save(aluguel);
    
    // Redirecionando para a lista de alugueis
    return new ModelAndView("redirect:/aluguel/");
}


    @GetMapping("/editar/{idAluguel}")
    public ModelAndView editForm(@PathVariable Long idAluguel) {
        Aluguel aluguel = aluguelService.findById(idAluguel);
        ModelAndView mv = new ModelAndView("aluguel_form");

        if (aluguel != null) {
            mv.addObject("aluguel", aluguel);

            List<Usuario> usuarios = usuarioService.findAll();
            List<Veiculo> veiculos = veiculoService.findAll();
            mv.addObject("usuarios", usuarios);
            mv.addObject("veiculos", veiculos);
        } else {
            mv.setViewName("not_found");
        }
        return mv;
    }

    @PostMapping("/editar")
    public ModelAndView update(@ModelAttribute Aluguel aluguel) {
        aluguelService.save(aluguel);
        return new ModelAndView("redirect:/aluguel/");
    }

    @GetMapping("/deletar/{idAluguel}")
    public ModelAndView delete(@PathVariable Long idAluguel) {
        aluguelService.delete(idAluguel);
        return new ModelAndView("redirect:/aluguel/");
    }

    @PostMapping("/buscarPorCpf")
    public ModelAndView buscarPorCpf(@RequestParam String cpf) {
        ModelAndView mv = new ModelAndView("aluguel_list");
        Usuario usuario = usuarioService.findByCpf(cpf);
        if (usuario != null) {
            List<Aluguel> alugueis = aluguelService.findByUsuarioId(usuario.getId());
            mv.addObject("alugueis", alugueis);
        } else {
            mv.addObject("mensagem", "Usuário não encontrado!");
            mv.addObject("alugueis", List.of());
        }
        return mv;
    }

    @GetMapping("/avaliar/{idAluguel}")
public ModelAndView avaliar(@PathVariable Long idAluguel) {
    ModelAndView mv = new ModelAndView("aluguel_avaliar");
    
    List<Aluguel> alugueisPendentes = aluguelService.findByStatus(Aluguel.Status.PENDENTE); // No more error here
    mv.addObject("alugueisPendentes", alugueisPendentes);
    mv.addObject("idAluguel", idAluguel); // Se precisar do id para algo

    return mv;
}

@PostMapping("/avaliar/status")
public ModelAndView avaliarStatus(@RequestParam Long idAluguel, @RequestParam String status) {
    Aluguel aluguel = aluguelService.findById(idAluguel);
    
    if (aluguel != null) {
        // Atualiza o status do aluguel
        if (status.equals("APROVADA")) {
            aluguel.setStatus(Aluguel.Status.APROVADA);
            // Salvar o aluguel atualizado
            aluguelService.save(aluguel);
            
            // Redireciona para a página de criar contrato com dados necessários
            ModelAndView modelAndView = new ModelAndView("criarContrato");
            modelAndView.addObject("aluguel", aluguel); // Passa o objeto aluguel
            return modelAndView;
        } else if (status.equals("RECUSADA")) {
            aluguel.setStatus(Aluguel.Status.RECUSADA);
            aluguelService.save(aluguel);
        }
        
        // Retornar uma mensagem em caso de não aprovação
        ModelAndView modelAndView = new ModelAndView("aluguel_avaliar");
        modelAndView.addObject("message", "Status do aluguel atualizado com sucesso!");
        return modelAndView;
    }
    
    // Se o aluguel não for encontrado
    return new ModelAndView("not_found");
}


    }