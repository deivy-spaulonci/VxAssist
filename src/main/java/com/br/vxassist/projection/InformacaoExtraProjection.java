package com.br.vxassist.projection;

import com.br.vxassist.model.InformacaoExtra;
import com.br.vxassist.model.TipoInformacaoExtra;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "informacaoExtraProjection", types = {InformacaoExtra.class})
public interface InformacaoExtraProjection {
    Long getId();
    String getNumero();
    TipoInformacaoExtra getTipoInformacaoExtra();
}
