package com.br.vxassist.filter;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.TipoDespesa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DespesaFilter implements Serializable {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("tipoDespesa")
    public TipoDespesa tipoDespesa;

    @JsonProperty("fornecedor")
    public Fornecedor fornecedor;

    @JsonProperty("dataInicial")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date dataInicial;

    @JsonProperty("dataFinal")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date dataFinal;

    @JsonProperty("formaPagamento")
    public FormaPagamento formaPagamento;

}

