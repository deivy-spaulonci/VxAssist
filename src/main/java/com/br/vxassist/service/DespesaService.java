package com.br.vxassist.service;

import com.br.vxassist.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DespesaService {
    public abstract Page<Despesa> getAll(Despesa despesa, Pageable pageable);
 }
