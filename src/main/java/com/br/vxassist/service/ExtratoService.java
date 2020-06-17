package com.br.vxassist.service;

import com.br.vxassist.model.Extrato;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExtratoService {
    public abstract Page<Extrato> getAll(Predicate predicate, Pageable pageable);

    public abstract Extrato save(Extrato extrato);
}
