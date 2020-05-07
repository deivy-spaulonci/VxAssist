package com.br.vxassist.projection;

import com.br.vxassist.model.TipoDespesa;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "tipoContaProjection", types = {TipoDespesa.class})
public interface TipoContaProjection {
    Long getId();
    String getNome();
    Boolean getContaCartao();
}
