package com.br.vxassist.builder;

import com.br.vxassist.dto.TipoDespesaDTO;
import lombok.Builder;

@Builder
public class TipoDespesaDTOBuilder {
    @Builder.Default
    private final Long id = 1L;
    @Builder.Default
    private final String nome = "Tipo de Despesa Teste";

    public TipoDespesaDTO buildTipoDespesaDTO(){
        return new TipoDespesaDTO(id, nome);
    }

}
