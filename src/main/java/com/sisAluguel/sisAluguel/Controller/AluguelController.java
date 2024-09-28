package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Service.AluguelService;

@Controller
@RequestMapping("/aluguel")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping("/")
    public ModelAndView getAll() {
        List<Aluguel> alugueis = aluguelService.findAll();
        ModelAndView mv = new ModelAndView("aluguel_list");
        mv.addObject("alugueis", alugueis);  // Mudei "alugueis" para "aluguels"
        return mv;
    }    


    // Exibe os detalhes de um aluguel específico
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

    // Exibe a tela de formulário para criar novo aluguel
    @GetMapping("/novo")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("aluguel_form");
        mv.addObject("aluguel", new Aluguel());
        return mv;
    }

    

    // Salva um novo aluguel
    @PostMapping("/novo")
    public ModelAndView create(Aluguel aluguel) {
        aluguelService.save(aluguel);
        return new ModelAndView("redirect:/aluguel/");
    }

    // Exibe a tela de formulário para editar um aluguel existente
    @GetMapping("/editar/{idAluguel}")
    public ModelAndView editForm(@PathVariable Long idAluguel) {
        Aluguel aluguel = aluguelService.findById(idAluguel);
        ModelAndView mv = new ModelAndView("aluguel_form");

        if (aluguel != null) {
            mv.addObject("aluguel", aluguel);
        } else {
            mv.setViewName("not_found");
        }
        return mv;
    }

    // Atualiza um aluguel existente
    @PostMapping("/editar")
    public ModelAndView update(Aluguel aluguel) {
        aluguelService.save(aluguel);
        return new ModelAndView("redirect:/aluguel/");
    }

    // Deleta um aluguel
    @GetMapping("/deletar/{idAluguel}")
    public ModelAndView delete(@PathVariable Long idAluguel) {
        aluguelService.delete(idAluguel);
        return new ModelAndView("redirect:/aluguel/");
    }
}
