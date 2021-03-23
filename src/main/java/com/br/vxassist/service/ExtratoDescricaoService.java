package com.br.vxassist.service;

import com.br.vxassist.dto.ExtratoDescricaoDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExtratoDescricaoService {
    public abstract List<ExtratoDescricaoDTO> get(Pageable pageable);

    public abstract ExtratoDescricaoDTO save(ExtratoDescricaoDTO extratoDescricaoDTO);
}
