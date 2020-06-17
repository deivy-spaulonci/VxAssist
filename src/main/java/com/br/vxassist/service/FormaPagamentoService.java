package com.br.vxassist.service;

import com.br.vxassist.model.FormaPagamento;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FormaPagamentoService {
    public abstract List<FormaPagamento> getAll(Pageable pageable);
}

