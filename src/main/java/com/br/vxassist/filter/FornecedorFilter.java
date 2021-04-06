package com.br.vxassist.filter;

import com.br.vxassist.model.Cidade;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
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


}
