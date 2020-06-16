package com.br.vxassist.service;

import com.br.vxassist.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

public interface DespesaService {
    public abstract Page<Despesa> getAll(Predicate predicate, Pageable pageable);

    public abstract Despesa save(Despesa despesa);
 }
