package com.br.vxassist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoDTO {
    private Long id;

    @Size(min = 3, message = "{size.forma-pagamento.nome}")
    @NotBlank(message = "{notblank.forma-pagamento.nome}")
    private String nome;
}
