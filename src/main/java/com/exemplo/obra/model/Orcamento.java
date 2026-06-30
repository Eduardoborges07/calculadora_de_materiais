package com.exemplo.obra.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orcamentos")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeUsuario;

    private Double areaTotal;

    // Resultados vindos do seu MaterialService
    private BigDecimal volumeConcretoCalculado;
    private Integer quantidadeTijolosCalculada;

    public Orcamento() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public Double getAreaTotal() { return areaTotal; }
    public void setAreaTotal(Double areaTotal) { this.areaTotal = areaTotal; }

    public BigDecimal getVolumeConcretoCalculado() { return volumeConcretoCalculado; }
    public void setVolumeConcretoCalculado(BigDecimal volumeConcretoCalculado) { this.volumeConcretoCalculado = volumeConcretoCalculado; }

    public Integer getQuantidadeTijolosCalculada() { return quantidadeTijolosCalculada; }
    public void setQuantidadeTijolosCalculada(Integer quantidadeTijolosCalculada) { this.quantidadeTijolosCalculada = quantidadeTijolosCalculada; }
}