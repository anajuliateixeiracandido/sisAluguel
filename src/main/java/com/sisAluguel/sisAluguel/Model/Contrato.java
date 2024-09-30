package com.sisAluguel.sisAluguel.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "contrato")
public class Contrato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Long idContrato;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_aluguel", nullable = false)
    private Aluguel aluguel;

    @NotNull
    private Double valor;

       @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private tipo tipo; 

    @NotNull
    private LocalDate data_inicio;

    @NotNull
    private LocalDate data_fim;

    enum tipo {
        CREDITO, 
        ASSINATURA, 
        QUILOMETRAGEM;
    }

    public Long getId() {
        return idContrato;
    }

    public void setId(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public tipo getTipo() {
        return tipo;
    }

    public void setTipo(tipo tipo) {
        this.tipo = tipo;
    }


    
}


/*

CREATE TABLE IF NOT EXISTS contrato(
	idContrato INT AUTO_INCREMENT PRIMARY KEY, 
    id_usuario INT, 
    id_aluguel INT, 
	tipo ENUM('CREDITO', 'ASSINATURA', 'QUILOMETRAGEM') NOT NULL,  
    data_inicio DATE NOT NULL, 
    data_fim DATE, 
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_aluguel) REFERENCES aluguel(idAluguel) ON DELETE CASCADE
);

 */