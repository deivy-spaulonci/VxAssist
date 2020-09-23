package com.br.vxassist.service;

import com.br.vxassist.filter.ContaFilter;
import com.br.vxassist.model.Conta;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ContaService {
    public abstract Page<Conta> getAll(ContaFilter contaFilter, Pageable pageable);

    public abstract Conta save(Conta conta);

    public abstract Long count();

    public abstract BigDecimal total(ContaFilter contaFilter);

    public abstract Conta findContaById(Long id);

    public abstract void excluirConta(Long id);

}
