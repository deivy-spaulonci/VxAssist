package com.br.vxassist.service;

import com.br.vxassist.model.ExtratoDescricao;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExtratoDescricaoService {
    public abstract List<ExtratoDescricao> getAll(Pageable pageable);

    public abstract ExtratoDescricao save(ExtratoDescricao extratoDescricao);
}
