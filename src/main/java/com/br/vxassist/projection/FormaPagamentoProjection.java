package com.br.vxassist.projection;

import com.br.vxassist.model.FormaPagamento;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "formaPagamentoProjection", types = {FormaPagamento.class})
public interface FormaPagamentoProjection {
    Long getId();
    String getNome();
}
