package com.br.vxassist.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoFilter implements Serializable {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("nome")
    public String nome;
}
