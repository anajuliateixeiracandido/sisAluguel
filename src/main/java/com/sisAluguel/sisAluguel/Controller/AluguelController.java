package com.sisAluguel.sisAluguel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sisAluguel.sisAluguel.Model.Aluguel;
import com.sisAluguel.sisAluguel.Service.AluguelService;


@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public List<Aluguel> getAll() {
        return aluguelService.findAll();
    }

    @GetMapping("/{idAluguel}")
    public ResponseEntity<Aluguel> getById(@PathVariable Long idAluguel) {
        Aluguel aluguel = aluguelService.findById(idAluguel);
        return aluguel != null ? ResponseEntity.ok(aluguel) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Aluguel create(@RequestBody Aluguel aluguel) {
        return aluguelService.save(aluguel);
    }

    @PutMapping("/{idAluguel}") 
    public ResponseEntity<Aluguel> update(@PathVariable Long idAluguel, @RequestBody Aluguel aluguel) {
        aluguel.setIdAluguel(idAluguel);
        Aluguel updatedAluguel = aluguelService.save(aluguel);
        return ResponseEntity.ok(updatedAluguel);
    }

    @DeleteMapping("/{idAluguel}")
    public ResponseEntity<Void> delete(@PathVariable Long idAluguel) {
    aluguelService.delete(idAluguel);
    return ResponseEntity.noContent().build();
    }
    
}
