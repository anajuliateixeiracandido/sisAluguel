package com.sisAluguel.sisAluguel.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "endereco")
public class Endereco {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idEndereco")
private Long idEndereco;

@NotNull
private String logradouro;

@NotNull
private String bairro;

@NotNull
private String cidade;

@NotNull
private String estado;

@NotNull
private String cep;

@NotNull
private Integer numero;

@Column(name = "complemento")
private String complemento;



// Getters and setters

public Long getId() {
    return idEndereco;
}

public void setId(Long idEndereco) {
    this.idEndereco = idEndereco;
}

public String getLogradouro() {
    return logradouro;
}

public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
}

public String getBairro() {
    return bairro;
}

public void setBairro(String bairro) {
    this.bairro = bairro;
}

public String getCidade() {
    return cidade;
}

public void setCidade(String cidade) {
    this.cidade = cidade;
}

public String getEstado() {
    return estado;
}

public void setEstado(String estado) {
    this.estado = estado;
}

public String getCep() {
    return cep;
}

public void setCep(String cep) {
    this.cep = cep;
}

public Integer getNumero() {
    return numero;
}

public void setNumero(Integer numero) {
    this.numero = numero;
}

public String getComplemento() {
    return complemento;
}

public void setComplemento(String complemento) {
    this.complemento = complemento;
}



}




/*
CREATE TABLE IF NOT EXISTS endereco(
	idEndereco INT AUTO_INCREMENT PRIMARY KEY, 
	logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(50) NOT NULL, 
    cidade VARCHAR(50) NOT NULL, 
    estado VARCHAR(50) NOT NULL, 
    cep VARCHAR(8) NOT NULL, 
    numero INT NOT NULL, 
    complemento VARCHAR(20) 
);
 */
