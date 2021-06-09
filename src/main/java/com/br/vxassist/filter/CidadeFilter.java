package com.br.vxassist.filter;

import com.br.vxassist.model.Estado;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeFilter implements Serializable {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("id")
    public String nome;

    @JsonProperty("estado")
    public Estado estado;

}
