package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sisAluguel.sisAluguel.Model.Endereco;
import com.sisAluguel.sisAluguel.Service.EnderecoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping 
    public List<Endereco> getAll() {
        return enderecoService.findAll();
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<Endereco> getById(@PathVariable Long idEndereco) {
        Endereco endereco = enderecoService.findById(idEndereco);
        return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Endereco create(@RequestBody Endereco endereco) {
        return enderecoService.save(endereco);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<Endereco> update(@PathVariable Long idEndereco, @RequestBody Endereco endereco) {
        endereco.setId(idEndereco);
        Endereco updatedEndereco = enderecoService.save(endereco);
        return ResponseEntity.ok(updatedEndereco);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable Long idEndereco) {
        enderecoService.delete(idEndereco);
        return ResponseEntity.noContent().build();
    }
    
    
}
