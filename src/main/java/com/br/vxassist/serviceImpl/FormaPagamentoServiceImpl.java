package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.mapper.FormaPagamentoMapper;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.QFormaPagamento;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.service.ServiceInterface;
import com.querydsl.core.BooleanBuilder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FormaPagamentoServiceImpl implements ServiceInterface<FormaPagamentoDTO, TipoFilter> {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private final FormaPagamentoMapper formaPagamentoMapper = FormaPagamentoMapper.INSTANCE;

    @Override
    public List<FormaPagamentoDTO> get(TipoFilter filter, Sort sort) {
        List<FormaPagamento> formasPagamento = new ArrayList<>();
        formaPagamentoRepository.findAll(getFormaPagamnetoPredicate(filter), sort).forEach(formasPagamento::add);
        return formaPagamentoMapper.toFormaPagamentoDtoList(formasPagamento);
    }

    @Override
    public Page<FormaPagamentoDTO> getPage(TipoFilter filter, Pageable peageble) {
        return null;
    }

    @Override
    public FormaPagamentoDTO create(FormaPagamentoDTO formaPagamentoDTO){
        return formaPagamentoMapper.toDTO(formaPagamentoRepository.save(formaPagamentoMapper.toModel(formaPagamentoDTO)));
    }

    @Override
    public void update(FormaPagamentoDTO formaPagamentoDTO) {
        formaPagamentoRepository.findById(formaPagamentoDTO.getId()).orElseThrow(EntityNotFoundException::new);
        formaPagamentoRepository.save(formaPagamentoMapper.toModel(formaPagamentoDTO));
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public FormaPagamentoDTO findById(Long id) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }

    private BooleanBuilder getFormaPagamnetoPredicate(TipoFilter tipoFilter){
        QFormaPagamento qFormaPagamento = QFormaPagamento.formaPagamento;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(tipoFilter)){
            if(Objects.nonNull(tipoFilter.id)){
                where.and(qFormaPagamento.id.stringValue().eq(tipoFilter.id.toString()));
            }
            if(Objects.nonNull(tipoFilter.nome) && Strings.isNotEmpty(tipoFilter.nome)){
                where.and(qFormaPagamento.nome.toLowerCase().contains(tipoFilter.nome.toLowerCase()));
            }
        }
        return where;
    }
}
