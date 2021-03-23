package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.mapper.FormaPagamentoMapper;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private final FormaPagamentoMapper formaPagamentoMapper = FormaPagamentoMapper.INSTANCE;

    @Override
    public List<FormaPagamentoDTO> get() {
        return formaPagamentoMapper.toFormaPagamentoDtoList(formaPagamentoRepository.findAll(Sort.by("nome").ascending()));
    }
}
