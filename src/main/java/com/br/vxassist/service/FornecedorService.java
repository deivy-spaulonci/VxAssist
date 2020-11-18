package com.br.vxassist.service;

import com.br.vxassist.filter.FornecedorFilter;
import com.br.vxassist.model.Fornecedor;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FornecedorService {
    abstract Page<Fornecedor> getAll(FornecedorFilter fornecedorFilter, Pageable pageable);

    abstract Fornecedor save(Fornecedor fornecedor);

    abstract Fornecedor findFornecedorById(Long id);
}
