package com.br.vxassist.service;

import com.br.vxassist.dto.FormaPagamentoDTO;

import java.util.List;

public interface FormaPagamentoService {
    public abstract List<FormaPagamentoDTO> get();

    abstract FormaPagamentoDTO save(FormaPagamentoDTO formaPagamentoDTO);

    abstract FormaPagamentoDTO findFormaPagamentoById(Long id);


}

