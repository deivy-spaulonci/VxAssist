package com.br.vxassist.service;

import com.br.vxassist.filter.CidadeFilter;
import com.br.vxassist.model.Cidade;
import com.br.vxassist.model.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CidadeService {

    abstract Page<Cidade> getAll(CidadeFilter cidadeFilter, Pageable pageable);

    abstract Cidade findCidadeById(Long id);
}
