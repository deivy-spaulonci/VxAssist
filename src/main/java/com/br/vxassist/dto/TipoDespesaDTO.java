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
public class TipoDespesaDTO {
    private Long id;

    @Size(min = 3, message = "{size.tipo-despesa.nome}")
    @NotBlank(message = "{notblank.tipo-despesa.nome}")
    private String nome;
}
