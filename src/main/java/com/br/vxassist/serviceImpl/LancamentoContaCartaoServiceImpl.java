package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.LancamentoContaCartao;
import com.br.vxassist.repository.LancamentoContaCartaoRepository;
import com.br.vxassist.service.LancamentoContaCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class LancamentoContaCartaoServiceImpl implements LancamentoContaCartaoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LancamentoContaCartaoRepository lancamentoContaCartaoRepository;

    @Override
    public List<LancamentoContaCartao> getAll() {
        return lancamentoContaCartaoRepository.findAll();
    }
}
