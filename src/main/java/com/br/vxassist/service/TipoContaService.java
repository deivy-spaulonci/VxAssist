package com.br.vxassist.service;

import com.br.vxassist.model.TipoConta;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoContaService {
    public abstract List<TipoConta> getAll(Pageable pageable);
}
