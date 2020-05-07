package com.br.vxassist.projection;

import com.br.vxassist.model.Conta;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.LancamentoContaCartao;
import com.br.vxassist.model.TipoConta;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Projection(name = "contaProjection", types = {Conta.class})
public interface ContaProjection {
    Long getId();
    String getNumero();
    String getCodigoBarra();
    TipoConta getTipoConta();
    Date getEmissao();
    Date getVencimento();
    int getParcela();
    int getTotalParcela();
    BigDecimal getValor();
    Date getDataPagamento();
    FormaPagamento getFormaPagamento();
    BigDecimal getValorPago();
    Boolean getCancelado();
    Long getIdCancelamento();
    String getObs();
    List<LancamentoContaCartao> getLancamentoContaCartao();
}
