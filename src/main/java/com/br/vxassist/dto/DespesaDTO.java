package com.br.vxassist.dto;

import com.br.vxassist.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DespesaDTO {

    private Long id;

    @NotNull(message = "{notnull.despesa.tipo-despesa}")
    private TipoDespesa tipoDespesa;

    @NotNull(message = "{notnull.despesa.fornecedor}")
    private Fornecedor fornecedor;

    @NotNull(message = "{notnull.despesa.data}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @NotNull(message = "{notnull.despesa.forma-pagamento}")
    private FormaPagamento formaPagamento;

    @NotNull(message = "{notnull.despesa.valor}")
    private BigDecimal valor;

    private List<InformacaoExtra> informacaoExtra;

    private String obs;

}
