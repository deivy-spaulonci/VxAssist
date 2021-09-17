package com.br.vxassist.service;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface TipoServiceInterface<D, F>{
    List<D> get(F filter);

    D create(D dto);

    void update(D dto);
}
