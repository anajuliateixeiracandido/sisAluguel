package com.sisAluguel.sisAluguel.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private String rg;

    @NotNull
    private String profissao;

    @NotNull
    private String entidadeEmpregadora;

    @NotNull
    private Double rendimentoAuferido;

    private String tipo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "idEndereco")
    private Endereco endereco;


    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEntidadeEmpregadora() {
        return entidadeEmpregadora;
    }

    public void setEntidadeEmpregadora(String entidadeEmpregadora) {
        this.entidadeEmpregadora = entidadeEmpregadora;
    }

    public Double getRendimentoAuferido() {
        return rendimentoAuferido;
    }

    public void setRendimentoAuferido(Double rendimentoAuferido) {
        this.rendimentoAuferido = rendimentoAuferido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    
}


/*
 * CREATE TABLE IF NOT EXISTS endereco(
	id INT AUTO_INCREMENT PRIMARY KEY, 
	logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(50) NOT NULL, 
    cidade VARCHAR(50) NOT NULL, 
    estado VARCHAR(50) NOT NULL, 
    cep VARCHAR(8) NOT NULL, 
    numero INT NOT NULL, 
    complemento VARCHAR(20) 
);
 */

 /*
  * 

  CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,  
    nome VARCHAR(255) NOT NULL,          
    cpf VARCHAR(14) NOT NULL,            
    rg VARCHAR(20), 
    tipo ENUM ('cliente', 'agente') NOT NULL,
    profissao VARCHAR(100),              
    entidade_empregadora VARCHAR(255),
    rendimento_auferido DOUBLE, 
    endereco_id INT,   -- Chave estrangeira para a tabela endereco
    FOREIGN KEY (endereco_id) REFERENCES endereco(id) ON DELETE CASCADE
);


 */