package com.br.vxassist.service;

import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Despesa;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface DespesaService {
    abstract Page<Despesa> getAll(DespesaFilter despesaFitler, Pageable pageable);

    abstract Despesa save(Despesa despesa);

    abstract Long count();

    abstract BigDecimal total(DespesaFilter despesaFilter);

    abstract Despesa findDespesaById(Long id);

    abstract void excluirDespesa(Long id);

 }
