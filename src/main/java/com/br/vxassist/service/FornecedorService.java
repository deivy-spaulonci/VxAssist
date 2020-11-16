package com.br.vxassist.service;

import com.br.vxassist.model.Fornecedor;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FornecedorService {
    public abstract Page<Fornecedor> getAll(Predicate predicate, Pageable pageable);

    public abstract Fornecedor save(Fornecedor fornecedor);
}
