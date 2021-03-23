package com.br.vxassist.dto;

import com.br.vxassist.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Estado estado;
}
