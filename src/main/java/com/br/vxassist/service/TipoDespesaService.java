package com.br.vxassist.service;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.TipoDespesaDTO;

import java.util.List;

public interface TipoDespesaService {
    public abstract List<TipoDespesaDTO> get();

    abstract TipoDespesaDTO save(TipoDespesaDTO tipoDespesaDTO);

    abstract TipoDespesaDTO findTipoDespesaById(Long id);
}
