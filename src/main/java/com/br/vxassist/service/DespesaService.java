package com.br.vxassist.service;

import com.br.vxassist.dto.DespesaDTO;
import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Despesa;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public interface DespesaService {

    abstract List<DespesaDTO> get(DespesaFilter despesaFitler);

    abstract List<DespesaDTO> get(DespesaFilter despesaFitler, Sort sort);

    abstract Page<DespesaDTO> getPage(DespesaFilter despesaFitler, Pageable pageable);

    abstract DespesaDTO findDespesaById(Long id);

    abstract DespesaDTO save(DespesaDTO despesaDTO);

    abstract Long count();

    abstract BigDecimal total(DespesaFilter despesaFilter);

    abstract void excluirDespesa(Long id);


 }
