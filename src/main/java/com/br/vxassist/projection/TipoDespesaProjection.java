package com.br.vxassist.projection;

import com.br.vxassist.model.TipoDespesa;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="tipoDespesaProjection", types = {TipoDespesa.class})
public interface TipoDespesaProjection {
    Long getId();
    String getNome();
}
