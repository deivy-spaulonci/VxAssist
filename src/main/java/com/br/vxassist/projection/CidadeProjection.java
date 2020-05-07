package com.br.vxassist.projection;

import com.br.vxassist.model.Cidade;
import com.br.vxassist.model.Estado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "cidadeProjection", types = {Cidade.class})
public interface CidadeProjection {
    Long getId();
    String getNome();
    Estado getEstado();
}
