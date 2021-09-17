package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.mapper.FormaPagamentoMapper;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.QFormaPagamento;
import com.br.vxassist.repository.FormaPagamentoRepository;
import com.br.vxassist.service.TipoServiceInterface;
import com.querydsl.core.BooleanBuilder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FormaPagamentoServiceImpl implements TipoServiceInterface<FormaPagamentoDTO, TipoFilter> {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private final FormaPagamentoMapper formaPagamentoMapper = FormaPagamentoMapper.INSTANCE;

    @Override
    public List<FormaPagamentoDTO> get(TipoFilter filter) {
        List<FormaPagamento> formasPagamento = new ArrayList<>();
        formaPagamentoRepository.findAll(getFormaPagamnetoPredicate(filter), Sort.by("nome").ascending()).forEach(formasPagamento::add);
        return formaPagamentoMapper.toFormaPagamentoDtoList(formasPagamento);
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
