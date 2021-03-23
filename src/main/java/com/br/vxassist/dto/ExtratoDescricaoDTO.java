package com.br.vxassist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoDescricaoDTO {
    private Long id;

    @Size(min = 3, message = "{size.extrato-descricao.nome}")
    @NotBlank(message = "{notblank.extrato-descricao.nome}")
    private String descricao;
}
