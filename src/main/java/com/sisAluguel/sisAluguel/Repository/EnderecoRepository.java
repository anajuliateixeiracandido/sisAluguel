package com.sisAluguel.sisAluguel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sisAluguel.sisAluguel.Model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
