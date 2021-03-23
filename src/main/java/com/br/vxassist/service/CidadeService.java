package com.br.vxassist.service;

import com.br.vxassist.dto.CidadeDTO;
import com.br.vxassist.filter.CidadeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CidadeService {

    abstract Page<CidadeDTO> getPage(CidadeFilter cidadeFilter, Pageable pageable);

    abstract List<CidadeDTO> get(CidadeFilter cidadeFilter, Sort sort);

    abstract CidadeDTO findCidadeById(Long id);
}
