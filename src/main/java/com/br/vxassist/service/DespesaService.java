package com.br.vxassist.service;

import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Despesa;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface DespesaService {
    public abstract Page<Despesa> getAll(Predicate predicate, Pageable pageable);

    public abstract Despesa save(Despesa despesa);

    public abstract Long count();

    public abstract BigDecimal total(DespesaFilter despesaFilter);
 }
