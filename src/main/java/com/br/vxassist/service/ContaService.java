package com.br.vxassist.service;

import com.br.vxassist.dto.ContaDTO;
import com.br.vxassist.filter.ContaFilter;
import com.br.vxassist.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public interface ContaService {

    public abstract List<ContaDTO> get(ContaFilter contaFitler, Sort sort);

    public abstract Page<ContaDTO> getPage(ContaFilter contaFilter, Pageable pageable);

    public abstract ContaDTO save(ContaDTO dontaDTO);

    public abstract Long count();

    public abstract BigDecimal total(ContaFilter contaFilter);

    public abstract ContaDTO findContaById(Long id);

    public abstract void excluirConta(Long id);

}
