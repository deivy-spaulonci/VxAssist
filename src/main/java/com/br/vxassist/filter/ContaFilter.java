package com.br.vxassist.filter;

import com.br.vxassist.model.TipoConta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ContaFilter implements Serializable {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("codigoBarra")
    public String codigoBarra;

    @JsonProperty("tipoConta")
    public TipoConta tipoConta;

    @JsonProperty("vencimentoInicial")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date vencimentoInicial;

    @JsonProperty("vencimentoFinal")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date vencimentoFinal;

    @QueryProjection
    public ContaFilter(Long id,
                       String codigBarra,
                       TipoConta tipoConta,
                       Date vencimentoInicial,
                       Date vencimentoFinal){
        super();
        this.id = id;
        this.codigoBarra = codigBarra;
        this.tipoConta = tipoConta;
        this.vencimentoInicial = vencimentoInicial;
        this.vencimentoFinal = vencimentoFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Date getVencimentoInicial() {
        return vencimentoInicial;
    }

    public void setVencimentoInicial(Date vencimentoInicial) {
        this.vencimentoInicial = vencimentoInicial;
    }

    public Date getVencimentoFinal() {
        return vencimentoFinal;
    }

    public void setVencimentoFinal(Date vencimentoFinal) {
        this.vencimentoFinal = vencimentoFinal;
    }
}
