package com.sisAluguel.sisAluguel.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idveiculo")
    private Long idVeiculo;

    @NotNull
    private String matricula;

    @NotNull
    private Integer ano;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private String placa;

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}

/*
 * CREATE TABLE IF NOT EXISTS veiculo(
 * idVeiculo INT AUTO_INCREMENT PRIMARY KEY,
 * matricula VARCHAR(20) NOT NULL,
 * ano INT NOT NULL,
 * marca VARCHAR(100) NOT NULL,
 * modelo VARCHAR(100) NOT NULL,
 * placa VARCHAR(08) NOT NULL
 * );
 */