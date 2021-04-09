package com.br.vxassist.dto;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.LancamentoContaCartao;
import com.br.vxassist.model.TipoConta;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;
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
    private Date emissao;

    @NotNull(message = "{notnull.conta.vencimento}")
    private Date vencimento;

    private int parcela;

    private int totalParcela;

    @NotNull(message = "{notnull.conta.valor}")
    private BigDecimal valor;

    private Date dataPagamento;

    private FormaPagamento formaPagamento;

    private BigDecimal valorPago;

    private Boolean cancelado;

    private Long idCancelamento;

    private String obs;

    private List<LancamentoContaCartao> lancamentoContaCartao;

    @JsonGetter(value = "status")
    public String getStatus() {
        switch (getIntStatus()) {
            case 1:
                return "Em aberto";
            case 0:
                return "Vencimento Hoje";
            case -1:
                return "Atrasado";
            case 2:
                return "Pago";
            case 3:
                return "Cancelado e substituido";
            default:
                return "Pago";
        }
    }

    @JsonGetter(value = "intStatus")
    public int getIntStatus() {
        if (getDataPagamento() == null) {
            Calendar currDtCal = Calendar.getInstance();
            currDtCal.setTime(new Date());
            currDtCal.set(Calendar.HOUR_OF_DAY, 0);
            currDtCal.set(Calendar.MINUTE, 0);
            currDtCal.set(Calendar.SECOND, 0);
            currDtCal.set(Calendar.MILLISECOND, 0);
            return getVencimento().compareTo(currDtCal.getTime());
        } else {
            return 2;
        }
    }
}
