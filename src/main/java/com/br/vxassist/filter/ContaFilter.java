package com.br.vxassist.filter;

import com.br.vxassist.model.TipoConta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
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

    @JsonProperty("emissaoInicial")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date emissaoInicial;

    @JsonProperty("emissaoFinal")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date emissaoFinal;


}
