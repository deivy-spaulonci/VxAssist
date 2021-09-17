package com.br.vxassist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDespesaDTO {

    private Long id;

    @NotBlank(message = "Nome do tipo de despesa está vazio")
    @NotNull(message = "Nome do tipo de despesa está nulo")
    @Size(min = 3, max = 255)
    private String nome;
}
