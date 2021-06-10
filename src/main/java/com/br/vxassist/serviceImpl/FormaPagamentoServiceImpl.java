package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.exception.IdNotFound;
import com.br.vxassist.mapper.FormaPagamentoMapper;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.service.TipoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoServiceImpl implements TipoServiceInterface<FormaPagamentoDTO> {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private final FormaPagamentoMapper formaPagamentoMapper = FormaPagamentoMapper.INSTANCE;

    @Override
    public List<FormaPagamentoDTO> get() {
        return formaPagamentoMapper.toFormaPagamentoDtoList(formaPagamentoRepository.findAll(Sort.by("nome").ascending()));
    }

    @Override
    public FormaPagamentoDTO save(FormaPagamentoDTO formaPagamentoDTO){
        return formaPagamentoMapper.toDTO(formaPagamentoRepository.save(formaPagamentoMapper.toModel(formaPagamentoDTO)));
    }

    @Override
    public FormaPagamentoDTO findById(Long id) {
        FormaPagamento formaPagamento = this.formaPagamentoRepository.findById(id).orElseThrow(IdNotFound::new);
        return formaPagamentoMapper.toDTO(formaPagamento);
    }
}
