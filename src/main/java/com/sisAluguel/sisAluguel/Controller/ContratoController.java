package com.sisAluguel.sisAluguel.Controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Model.Contrato;
import com.sisAluguel.sisAluguel.Model.Usuario;
import com.sisAluguel.sisAluguel.Service.AluguelService;
import com.sisAluguel.sisAluguel.Service.ContratoService;
import com.sisAluguel.sisAluguel.Service.UsuarioService;

@Controller
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ModelAndView getAll() {
        List<Contrato> contratos = contratoService.findAll();
        ModelAndView mv = new ModelAndView("contrato_list");
        mv.addObject("contratos", contratos);
        return mv;
    }

    @GetMapping("/{idContrato}")
    public ModelAndView getById(@PathVariable Long idContrato) {
        Contrato contrato = contratoService.findById(idContrato);
        ModelAndView mv = new ModelAndView("contrato_detalhes");

        if (contrato != null) {
            mv.addObject("contrato", contrato);
        } else {
            mv.setViewName("not_found");
        }
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView createForm(@RequestParam("idAluguel") Long aluguelId,
            @RequestParam("idUsuario") Long usuarioId) {
        ModelAndView mv = new ModelAndView("contrato_form");
        Contrato contrato = new Contrato();

        Aluguel aluguel = aluguelService.findById(aluguelId);
        Usuario usuario = usuarioService.findById(usuarioId);

        if (aluguel != null && usuario != null) {
            contrato.setAluguel(aluguel);
            contrato.setUsuario(usuario);
        }

        mv.addObject("contrato", contrato); // Passa o objeto Contrato com o Aluguel e o Usuário definidos
        return mv;
    }

    @GetMapping("/novo/manual")
    public ModelAndView createManualForm() {
        ModelAndView mv = new ModelAndView("contrato_manual_form");
        Contrato contrato = new Contrato();

        // Carrega todos os aluguéis e usuários disponíveis para seleção
        List<Aluguel> alugueis = aluguelService.findAll();
        List<Usuario> usuarios = usuarioService.findAll();

        mv.addObject("alugueis", alugueis);
        mv.addObject("usuarios", usuarios);
        mv.addObject("contrato", contrato);
        return mv;
    }

    @PostMapping("/novo/manual")
    public ModelAndView createManual(@ModelAttribute Contrato contrato, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("contrato_manual_form");
            mv.addObject("error", "Erro na criação do contrato.");
            return mv;
        }

        // Aqui ele já vai ter os IDs de aluguel e usuário preenchidos pelo formulário
        Aluguel aluguel = aluguelService.findById(contrato.getAluguel().getIdAluguel());
        Usuario usuario = usuarioService.findById(contrato.getUsuario().getId());

        if (aluguel == null || usuario == null) {
            ModelAndView mv = new ModelAndView("contrato_manual_form");
            mv.addObject("error", "Aluguel ou Usuário não encontrado.");
            return mv;
        }

        if (contrato.getMetodoPagamento() == Contrato.MetodoPagamento.cartao_debito
                || contrato.getMetodoPagamento() == Contrato.MetodoPagamento.boleto) {
            contrato.setNumParcela(1);
        }

        contrato.setAluguel(aluguel);
        contrato.setUsuario(usuario);
        contratoService.save(contrato);

        return new ModelAndView("redirect:/contrato/");
    }

    @PostMapping("/novo")
    public ModelAndView create(@ModelAttribute("contrato") Contrato contrato, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("contrato_form");
            mv.addObject("contrato", contrato); // Passar o contrato com dados preenchidos
            mv.addObject("error", "Erro na validação dos dados.");
            return mv;
        }

        // Processamento do contrato e redirecionamento
        contratoService.save(contrato);
        return new ModelAndView("redirect:/contrato/");
    }

    @GetMapping("/editar/{idContrato}")
public ModelAndView editForm(@PathVariable Long idContrato) {
    Contrato contrato = contratoService.findById(idContrato);
    ModelAndView mv = new ModelAndView("contrato_detalhes"); // Reutiliza a view de formulário de edição

    if (contrato != null) {
        // Format dates to "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedStartDate = contrato.getData_inicio().format(formatter);
        String formattedEndDate = contrato.getData_fim().format(formatter);
        
        // Add formatted dates to the ModelAndView
        mv.addObject("formattedStartDate", formattedStartDate);
        mv.addObject("formattedEndDate", formattedEndDate);
        
        // Add contrato to the ModelAndView
        mv.addObject("contrato", contrato);

        // Carrega os usuários e alugueis disponíveis para exibição no formulário
        List<Usuario> usuarios = usuarioService.findAll();
        List<Aluguel> alugueis = aluguelService.findAll();
        mv.addObject("usuarios", usuarios);
        mv.addObject("alugueis", alugueis);
    } else {
        mv.setViewName("not_found");
    }
    return mv;
}

@PutMapping("/editar/{idContrato}")
public ModelAndView update(@PathVariable Long idContrato, @ModelAttribute Contrato contrato, BindingResult result) {
    if (result.hasErrors()) {
        ModelAndView mv = new ModelAndView("contrato_detalhes"); // Reutiliza a página de detalhes
        mv.addObject("error", "Erro na validação dos dados.");
        return mv;
    }

    Contrato existingContrato = contratoService.findById(idContrato);
    if (existingContrato == null) {
        return new ModelAndView("not_found");
    }

    // Verificar se o aluguel existe e não é null
    Aluguel aluguel = contrato.getAluguel();
    if (aluguel == null || aluguel.getIdAluguel() == null) {
        ModelAndView mv = new ModelAndView("contrato_detalhes");
        mv.addObject("error", "Aluguel não encontrado ou não especificado.");
        return mv;
    }

    // Verificar se o usuário existe e não é null
    Usuario usuario = contrato.getUsuario();
    if (usuario == null || usuario.getId() == null) {
        ModelAndView mv = new ModelAndView("contrato_detalhes");
        mv.addObject("error", "Usuário não encontrado ou não especificado.");
        return mv;
    }

    // Atualiza os campos necessários
    existingContrato.setAluguel(aluguel);
    existingContrato.setUsuario(usuario);
    existingContrato.setValor(contrato.getValor());
    existingContrato.setMetodoPagamento(contrato.getMetodoPagamento());
    existingContrato.setNumParcela(contrato.getNumParcela());
    existingContrato.setTipo(contrato.getTipo());
    existingContrato.setData_inicio(contrato.getData_inicio());
    existingContrato.setData_fim(contrato.getData_fim());

    // Salvar o contrato atualizado
    contratoService.save(existingContrato);

    return new ModelAndView("redirect:/contrato/");
}



    @DeleteMapping("/deletar/{idContrato}")
    public ModelAndView delete(@PathVariable Long idContrato) {
        // Verifica se o contrato existe antes de excluir
        Contrato existingContrato = contratoService.findById(idContrato);
        if (existingContrato == null) {
            return new ModelAndView("not_found");
        }

        contratoService.delete(idContrato);
        return new ModelAndView("redirect:/contrato/");
    }
}
