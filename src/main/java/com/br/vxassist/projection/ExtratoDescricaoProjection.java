package com.br.vxassist.projection;

import com.br.vxassist.model.ExtratoDescricao;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "extratoDescricaoProjection", types = {ExtratoDescricao.class})
public interface ExtratoDescricaoProjection {
    Long getId();
    String getDescricao();
}