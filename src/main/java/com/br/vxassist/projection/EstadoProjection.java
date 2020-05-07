package com.br.vxassist.projection;

import com.br.vxassist.model.Estado;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "estadoProjection", types = {Estado.class})
public interface EstadoProjection {
    Long getId();
    String getNome();
    String getSigla();
}
