package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.LancamentoContaCartaoDTO;
import com.br.vxassist.mapper.LancamentoContaCartaoMapper;
import com.br.vxassist.repository.LancamentoContaCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoContaCartaoServiceImpl {

    @Autowired
    private LancamentoContaCartaoRepository lancamentoContaCartaoRepository;

    private final LancamentoContaCartaoMapper lancamentoContaCartaoMapper = LancamentoContaCartaoMapper.INSTANCE;

    public List<LancamentoContaCartaoDTO> get() {
        return lancamentoContaCartaoMapper.toLancamentoContaCartaoaDtoList(lancamentoContaCartaoRepository.findAll());
    }
}

