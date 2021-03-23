package com.br.vxassist.service;

import com.br.vxassist.dto.ExtratoDTO;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExtratoService {
    public abstract Page<ExtratoDTO> getPage(Predicate predicate, Pageable pageable);

    public abstract ExtratoDTO save(ExtratoDTO extratoDTO);
}
