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
public class TipoContaDTO {
    private Long id;

    @Size(min = 3, message = "{size.tipo-conta.nome}")
    @NotBlank(message = "{Nome da Conta inválido}")
    private String nome;

    private Boolean contaCartao;
}
