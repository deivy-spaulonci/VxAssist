package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Override
    public List<FormaPagamento> getAll(Pageable pageable) {
        return formaPagamentoRepository.findAll(pageable).getContent();
    }
}
