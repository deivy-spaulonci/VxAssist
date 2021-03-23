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
public class EstadoDTO {

    private Long id;

    @NotBlank(message = "{nobblank.estado.nome}")
    private String nome;

    @Size(min = 2, max = 2, message = "{size.estado.sigla}")
    @NotBlank(message = "{notblank.estado.sigla}")
    private String sigla;
}
