package com.br.vxassist.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ServiceInterface<D, F>{
    List<D> get(F filter, Sort sort);

    Page<D> getPage(F filter, Pageable peageble);

    D create(D dto);

    Long count();

    D findById(Long id);

    void excluir(Long id);
}
