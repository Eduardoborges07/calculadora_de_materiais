package com.exemplo.obra.controller;

import com.exemplo.obra.model.Orcamento;
import com.exemplo.obra.repository.OrcamentoRepository;
import com.exemplo.obra.service.MaterialService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class OrcamentoBean implements Serializable {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private OrcamentoRepository repository;

    private Orcamento orcamento = new Orcamento();

    private String nomeBusca;
    private List<Orcamento> listaOrcamentos;

    public void calcularESalvar() {
        try {
            double ladoBase = Math.sqrt(orcamento.getAreaTotal());
            List<Object> arestasSimuladas = new ArrayList<>(); // Adapte para sua classe Aresta real

            repository.save(orcamento);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Orçamento nº " + orcamento.getId() + " salvo!"));

            orcamento = new Orcamento(); // Limpa form
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao calcular: " + e.getMessage()));
        }
    }

    public void buscarPorNome() {
        listaOrcamentos = repository.findByNomeUsuarioContainingIgnoreCase(nomeBusca);
    }

    // Getters e Setters
    public Orcamento getOrcamento() { return orcamento; }
    public void setOrcamento(Orcamento orcamento) { this.orcamento = orcamento; }

    public String getNomeBusca() { return nomeBusca; }
    public void setNomeBusca(String nomeBusca) { this.nomeBusca = nomeBusca; }

    public List<Orcamento> getListaOrcamentos() { return listaOrcamentos; }
}
