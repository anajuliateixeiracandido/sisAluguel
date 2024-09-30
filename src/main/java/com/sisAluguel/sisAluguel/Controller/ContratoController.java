package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sisAluguel.sisAluguel.Model.Contrato;
import com.sisAluguel.sisAluguel.Service.ContratoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/contrato")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public List<Contrato> getAll() {
        return contratoService.findAll();
    }

    @GetMapping("/{idContrato}")
    public ResponseEntity<Contrato> getById(@PathVariable Long idContrato) {
        Contrato contrato = contratoService.findById(idContrato);
        return contrato != null ? ResponseEntity.ok(contrato) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute Contrato contrato) {

        contratoService.save(contrato);

        return new ModelAndView("redirect:/visualizarContratos/");
    }


    @PutMapping("/{idContrato}")
    public ResponseEntity<Contrato> update(@PathVariable Long idContrato, @RequestBody Contrato contrato) {
       contrato.setId(idContrato);
       Contrato updatedContrato = contratoService.save(contrato);
       return ResponseEntity.ok(updatedContrato);
    }

    @DeleteMapping("/{idContrato}")
    public ResponseEntity<Void> delete(@PathVariable Long idContrato) {
        contratoService.delete(idContrato);
        return ResponseEntity.noContent().build();
    }
}
