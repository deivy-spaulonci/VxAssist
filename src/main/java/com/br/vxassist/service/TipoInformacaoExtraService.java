package com.br.vxassist.service;

import com.br.vxassist.model.TipoInformacaoExtra;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TipoInformacaoExtraService {
    public abstract List<TipoInformacaoExtra> getAll(Pageable pageable);
}
