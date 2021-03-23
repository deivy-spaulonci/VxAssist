package com.br.vxassist.dto;

import com.br.vxassist.model.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoContaCartaoDTO {
    private Long id;

    @NotNull(message = "{notnull.lancamento-conta-cartao.data}")
    private Date data;

    private Fornecedor fornecedor;

    @NotEmpty(message = "Valor vazio!")
    private BigDecimal valor;

    private int parcela;

    private int totalParcela;
}
