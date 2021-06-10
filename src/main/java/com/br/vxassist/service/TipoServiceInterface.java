package com.br.vxassist.service;

import java.util.List;

public interface TipoServiceInterface<D>{
    List<D> get();

    D save(D dto);

    D findById(Long id);
}
