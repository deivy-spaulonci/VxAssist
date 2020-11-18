package com.br.vxassist.filter;

import com.br.vxassist.model.Cidade;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import java.io.Serializable;

public class FornecedorFilter implements Serializable {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("nome")
    public String nome;

    @JsonProperty("razaoSocial")
    public String razaoSocial;

    @JsonProperty("cnpj")
    public String cnpj;

    @JsonProperty("cidade")
    public Cidade cidade;

    @QueryProjection
    public FornecedorFilter(Long id,
                            String nome,
                            String razaoSocial,
                            String cnpj,
                            Cidade cidade){
        super();
        this.id = id;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
    }

}
