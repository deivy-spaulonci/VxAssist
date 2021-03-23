package com.br.vxassist.dto;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.LancamentoContaCartao;
import com.br.vxassist.model.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {
    private Long id;

    private String numero;

    private String codigoBarra;

    @NotNull(message = "{notnull.conta.tipo-conta}")
    private TipoConta tipoConta;

    @NotNull(message = "{notnull.conta.emissao}")
    @NotEmpty(message = "{notempty.conta.emissao}")
    private Date emissao;

    @NotNull(message = "{notnull.conta.vencimento}")
    @NotEmpty(message = "{notempty.conta.vencimento}")
    private Date vencimento;

    private int parcela;

    private int totalParcela;

    @NotNull(message = "{notnull.conta.valor}")
    @NotEmpty(message = "{notempty.conta.valor}")

    private BigDecimal valor;

    private Date dataPagagamento;

    private FormaPagamento formaPagamento;

    private BigDecimal valorPago;

    private Boolean cancelado;

    private Long idCancelamento;

    private String obs;

    private List<LancamentoContaCartao> lancamentoContaCartao;
}
