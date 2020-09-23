package com.br.vxassist.filter;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.TipoDespesa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

    @QueryProjection
    public DespesaFilter(Long id,
                         TipoDespesa tipoDespesa,       
                         Fornecedor fornecedor,
                         Date dataInicial,
                         Date dataFinal,
                         FormaPagamento formaPagamento) {
        super();
        this.id = id;
        this.tipoDespesa = tipoDespesa;
        this.fornecedor = fornecedor;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.formaPagamento = formaPagamento;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}

