package com.sisAluguel.sisAluguel.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sisAluguel.sisAluguel.Model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
}
