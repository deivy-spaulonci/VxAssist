package com.br.vxassist.filter;

import com.br.vxassist.model.Estado;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import java.io.Serializable;

public class CidadeFilter implements Serializable {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("id")
    public String nome;

    @JsonProperty("estado")
    public Estado estado;

    @QueryProjection
    public CidadeFilter(Long id,
                        String nome,
                        Estado estado){
        super();
        this.id=id;
        this.nome=nome;
        this.estado = estado;
    }

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
