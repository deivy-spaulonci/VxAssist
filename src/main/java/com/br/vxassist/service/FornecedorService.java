package com.br.vxassist.service;

import com.br.vxassist.dto.FornecedorDTO;
import com.br.vxassist.filter.FornecedorFilter;
import com.br.vxassist.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface FornecedorService {
    abstract Page<FornecedorDTO> getPage(FornecedorFilter fornecedorFilter, Pageable pageable);

    abstract List<FornecedorDTO> get(FornecedorFilter fornecedorFilter, Sort sort);

    abstract FornecedorDTO save(FornecedorDTO fornecedorDTO);

    abstract FornecedorDTO findFornecedorById(Long id);
}
