package com.br.vxassist.service;

import com.br.vxassist.model.TipoDespesa;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoDespesaService {
    public abstract List<TipoDespesa> getAll(Pageable pageable);
}
