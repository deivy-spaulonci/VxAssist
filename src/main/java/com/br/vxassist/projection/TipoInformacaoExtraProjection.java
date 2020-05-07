package com.br.vxassist.projection;

import com.br.vxassist.model.TipoInformacaoExtra;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "tipoInformacaoExtraProjection", types = {TipoInformacaoExtra.class})
public interface TipoInformacaoExtraProjection {
    Long getId();
    String getNome();
}
