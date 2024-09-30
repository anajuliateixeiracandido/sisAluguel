package com.sisAluguel.sisAluguel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sisAluguel.sisAluguel.Model.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

        
}
