package com.br.vxassist.dto;

import com.br.vxassist.model.TipoInformacaoExtra;
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
public class InformacaoExtraDTO {
    private Long id;

    @Size(min = 3, message = "{size.informacao-extra.nome}")
    @NotBlank(message = "{notblank.informacao-extra.nome}")
    private String numero;

    private TipoInformacaoExtra tipoInformacaoExtra;
}
