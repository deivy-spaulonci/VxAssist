package com.br.vxassist.projection;

import com.br.vxassist.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Projection(name = "depesaProjection", types = { Despesa.class })
public interface DespesaProjection {

    @Value("#{target.id}")
    Long getId();
    TipoDespesa getTipoDespesa();
//    Fornecedor getFornecedor();
//    Date getData();
//    FormaPagamento getFormaPagamento();
//    BigDecimal getValor();
//    String getObs();
//    List<InformacaoExtra> getInformacaoExtra();

}
