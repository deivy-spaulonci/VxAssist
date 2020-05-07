package com.br.vxassist.projection;

import com.br.vxassist.model.Extrato;
import com.br.vxassist.model.ExtratoDescricao;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.Date;

@Projection(name = "extratoProjection", types = {Extrato.class})
public interface ExtratoProjection {
    Long getId();
    Date getData();
    ExtratoDescricao getDescricao();
    String getDocumento();
    BigDecimal getMovimento();

}
