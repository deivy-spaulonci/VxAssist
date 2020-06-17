package com.br.vxassist.service;

import com.br.vxassist.model.Cidade;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CidadeService {
    public abstract List<Cidade> getAll(Pageable pageable);
}
