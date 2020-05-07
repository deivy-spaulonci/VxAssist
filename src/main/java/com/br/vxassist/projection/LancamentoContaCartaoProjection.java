package com.br.vxassist.projection;

import com.br.vxassist.model.LancamentoContaCartao;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.Date;

@Projection(name = "lancamentoContaCartaoProjection", types = {LancamentoContaCartao.class})
public interface LancamentoContaCartaoProjection {
    Long getId();
    Date getData();
    BigDecimal getValor();
    int getParcela();
    int getTotalParcela();

}
