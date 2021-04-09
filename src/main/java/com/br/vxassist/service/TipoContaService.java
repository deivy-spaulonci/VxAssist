package com.br.vxassist.service;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.TipoContaDTO;

import java.util.List;

public interface TipoContaService {
    public abstract List<TipoContaDTO> get();

    abstract TipoContaDTO save(TipoContaDTO tipoContaDTO);

    abstract TipoContaDTO findTipoContaById(Long id);

}
