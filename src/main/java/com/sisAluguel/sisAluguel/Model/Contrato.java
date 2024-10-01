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
    private TipoPlano tipo;

    @NotNull
    private LocalDate data_inicio;

    @NotNull
    private LocalDate data_fim;

    @NotNull
    @Column(name = "num_parcela")
    private Integer numParcela;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "metodo_pagamento")
    private MetodoPagamento metodoPagamento;


    public enum MetodoPagamento {
        cartao_credito,
        cartao_debito,
        boleto
    }

    public enum TipoPlano {
        BRONZE, PRATA, OURO;
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

    public TipoPlano getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlano tipo) {
        this.tipo = tipo;
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

    

}
