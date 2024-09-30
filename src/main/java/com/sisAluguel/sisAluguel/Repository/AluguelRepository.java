package com.sisAluguel.sisAluguel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sisAluguel.sisAluguel.Model.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findByUsuarioId(Long usuarioId);
    List<Aluguel> findByStatus(Aluguel.Status status);

}
