package com.br.vxassist.dto;

import com.br.vxassist.model.ExtratoDescricao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoDTO {
    private Long id;

    @NotNull(message = "{notnull.extrato.data}")
    @Temporal(TemporalType.DATE)
    private Date data;

    private ExtratoDescricao descricao;

    private String documento;

    private BigDecimal movimento;
}
