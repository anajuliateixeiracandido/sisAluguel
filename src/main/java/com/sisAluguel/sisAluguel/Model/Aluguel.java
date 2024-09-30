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

@Entity
@Table(name = "aluguel")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaluguel")
    private Long idAluguel;

    @NotNull
    private Double valor;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "metodo_pagamento")
    private MetodoPagamento metodoPagamento;

    @NotNull
    private Integer numParcela;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private Status status;

    public Long getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(Long idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    enum MetodoPagamento {
        DEBITO,
        CREDITO,
        BOLETO;
    }

    public enum Status {
        PENDENTE,
        APROVADA,
        RECUSADA
    }
}

/*
 * CREATE TABLE IF NOT EXISTS aluguel(
 * idAluguel INT AUTO_INCREMENT PRIMARY KEY,
 * valor DOUBLE NOT NULL,
 * metodo_pagamento ENUM ('débito', 'crédito', 'boleto'),
 * numParcela INT,
 * idUsuario INT,
 * idVeiculo INT,
 * status ENUM ('aprovada', 'pendente', 'recusada') NOT NULL,
 * FOREIGN KEY (idUsuario) REFERENCES usuario(id) ON DELETE CASCADE,
 * FOREIGN KEY (idVeiculo) REFERENCES veiculo(idVeiculo) ON DELETE CASCADE
 * );
 */
