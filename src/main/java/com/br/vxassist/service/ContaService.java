package com.br.vxassist.service;

import com.br.vxassist.model.Conta;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContaService {
    public abstract Page<Conta> getAll(Predicate predicate, Pageable pageable);

    public abstract Conta save(Conta conta);
}
