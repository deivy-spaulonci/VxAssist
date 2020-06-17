package com.br.vxassist.service;

import com.br.vxassist.model.LancamentoContaCartao;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoContaCartaoService {

    public abstract List<LancamentoContaCartao> getAll(Pageable pageable);

}
