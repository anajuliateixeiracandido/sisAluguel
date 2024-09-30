package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sisAluguel.sisAluguel.Model.Veiculo;
import com.sisAluguel.sisAluguel.Service.VeiculoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;




@Controller
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;


    
    
  // Método para exibir o formulário de cadastro de veículo
  @GetMapping("/cadastro")
  public ModelAndView createForm() {
      ModelAndView mv = new ModelAndView("cadastroVeiculo");
      mv.addObject("veiculo", new Veiculo());
      return mv;
  }

  // Método para salvar um novo veículo
  @PostMapping("/cadastro")
  public ModelAndView create(@ModelAttribute("veiculo") Veiculo veiculo) {
      veiculoService.save(veiculo);
      return new ModelAndView("redirect:/veiculo/lista/agente"); // Redireciona para a lista de veículos
  }

  // Método para listar todos os veículos
  @GetMapping("/lista")
  public ModelAndView listarVeiculos() {
      List<Veiculo> veiculos = veiculoService.findAll();
      ModelAndView mv = new ModelAndView("listaVeiculos");
      mv.addObject("veiculo", veiculos);
      return mv;
  }

  @GetMapping("/lista/agente")
  public ModelAndView listarVeiculosParaAgentes(@ModelAttribute("mensagem") String mensagem) {
      List<Veiculo> veiculos = veiculoService.findAll();
      ModelAndView mv = new ModelAndView("listaVeiculosAgente");
      mv.addObject("veiculos", veiculos);
      
      if (mensagem != null && !mensagem.isEmpty()) {
          mv.addObject("mensagem", mensagem);
      }
      
      return mv;
  }

  @PostMapping("/editar/{id}")
  public ModelAndView editarVeiculo(@PathVariable("id") Long idVeiculo, @ModelAttribute Veiculo veiculoAtualizado) {
      ModelAndView modelAndView = new ModelAndView("redirect:/veiculo/lista/agente");
      
      try {
          Veiculo veiculo = veiculoService.findById(idVeiculo);
          
          if (veiculo == null) {
              modelAndView.addObject("mensagem", "Veículo não encontrado!");
              return modelAndView;
          }

          veiculo.setMatricula(veiculoAtualizado.getMatricula());
          veiculo.setAno(veiculoAtualizado.getAno());
          veiculo.setMarca(veiculoAtualizado.getMarca());
          veiculo.setModelo(veiculoAtualizado.getModelo());
          veiculo.setPlaca(veiculoAtualizado.getPlaca());
          
          veiculoService.save(veiculo);
          
          modelAndView.addObject("mensagem", "Veículo atualizado com sucesso!");
      } catch (Exception e) {
          modelAndView.addObject("mensagem", "Erro ao atualizar veículo: " + e.getMessage());
          e.printStackTrace();
      }

      return modelAndView;
  }

    @PostMapping("/deletar/{id}")
    public String deletarVeiculo(@PathVariable("id") Long idVeiculo, RedirectAttributes redirectAttributes) {
        try {
            Veiculo veiculo = veiculoService.findById(idVeiculo);
            
            if (veiculo == null) {
                redirectAttributes.addFlashAttribute("mensagem", "Veículo não encontrado!");
            } else {
                veiculoService.delete(idVeiculo);
                redirectAttributes.addFlashAttribute("mensagem", "Veículo deletado com sucesso!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao deletar veículo: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/veiculo/lista/agente";
    }
    


  //  @GetMapping
   // public List<Veiculo> getAll() {
    //    return veiculoService.findAll();
  //  }

  //  @GetMapping("/{idVeiculo}")
  //  public ResponseEntity<Veiculo> getById(@PathVariable Long idVeiculo) {
//Veiculo veiculo = veiculoService.findById(idVeiculo);
   //     return veiculo != null ? ResponseEntity.ok(veiculo) : ResponseEntity.notFound().build();
  //  }

 //  @PostMapping 
  ////  public Veiculo create(@RequestBody Veiculo veiculo) {
    //    return veiculoService.save(veiculo);
  //  }

   // @PutMapping("/{idVeiculo}")
   // public ResponseEntity<Veiculo> update(@PathVariable Long idVeiculo, @RequestBody Veiculo veiculo) {
   //     veiculo.setId(idVeiculo);
    //    Veiculo updatedVeiculo = veiculoService.save(veiculo);
    //    return ResponseEntity.ok(updatedVeiculo);
  //  }


    
}
