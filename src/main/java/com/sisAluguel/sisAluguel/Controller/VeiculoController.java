package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sisAluguel.sisAluguel.Model.Veiculo;
import com.sisAluguel.sisAluguel.Service.VeiculoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> getAll() {
        return veiculoService.findAll();
    }

    @GetMapping("/{idVeiculo}")
    public ResponseEntity<Veiculo> getById(@PathVariable Long idVeiculo) {
        Veiculo veiculo = veiculoService.findById(idVeiculo);
        return veiculo != null ? ResponseEntity.ok(veiculo) : ResponseEntity.notFound().build();
    }

    @PostMapping 
    public Veiculo create(@RequestBody Veiculo veiculo) {
        return veiculoService.save(veiculo);
    }

    @PutMapping("/{idVeiculo}")
    public ResponseEntity<Veiculo> update(@PathVariable Long idVeiculo, @RequestBody Veiculo veiculo) {
        veiculo.setId(idVeiculo);
        Veiculo updatedVeiculo = veiculoService.save(veiculo);
        return ResponseEntity.ok(updatedVeiculo);
    }

    @DeleteMapping("/{idVeiculo}")
    public ResponseEntity<Void> delete(@PathVariable Long idVeiculo) {
        veiculoService.delete(idVeiculo);
        return ResponseEntity.noContent().build();
    }
    
}
