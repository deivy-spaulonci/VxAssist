package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.TipoContaDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.mapper.TipoContaMapper;
import com.br.vxassist.model.QTipoConta;
import com.br.vxassist.model.TipoConta;
import com.br.vxassist.repository.TipoContaRepository;
import com.br.vxassist.service.ServiceInterface;
import com.querydsl.core.BooleanBuilder;
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
public class TipoContaServiceImpl implements ServiceInterface<TipoContaDTO, TipoFilter> {

    @Autowired
    private TipoContaRepository tipoContaRepository;

    private final TipoContaMapper tipoContaMapper = TipoContaMapper.INSTANCE;

    @Override
    public List<TipoContaDTO> get(TipoFilter tipoFilter, Sort sort) {
        List<TipoConta> tiposContas = new ArrayList<>();
        tipoContaRepository.findAll(this.getTipoContaPredicate(tipoFilter), sort).forEach(tiposContas::add);
        return tipoContaMapper.toTipoContaDtoList(tiposContas);
    }

    @Override
    public Page<TipoContaDTO> getPage(TipoFilter filter, Pageable peageble) {
        return null;
    }

    @Override
    public TipoContaDTO create(TipoContaDTO tipoContaDTO) {
        return tipoContaMapper.toDTO(tipoContaRepository.save(tipoContaMapper.toModel(tipoContaDTO)));
    }

    @Override
    public void update(TipoContaDTO tipoContaDTO) {
        tipoContaRepository.findById(tipoContaDTO.getId()).orElseThrow(EntityNotFoundException::new);
        tipoContaRepository.save(tipoContaMapper.toModel(tipoContaDTO));
    }

    @Override
    public Long count() {
        return null;
    }

    public TipoContaDTO findById(Long id) {
        TipoConta tipoConta = this.tipoContaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return tipoContaMapper.toDTO(tipoConta);
    }

    @Override
    public void excluir(Long id) {

    }

    public BooleanBuilder getTipoContaPredicate(TipoFilter tipoFilter){
        QTipoConta qTipoConta = QTipoConta.tipoConta;
        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(tipoFilter.id)){
            where.and(qTipoConta.id.stringValue().eq(tipoFilter.id.toString()));
        }
        if(Objects.nonNull(tipoFilter.nome) && !tipoFilter.nome.trim().isEmpty()){
            where.and(qTipoConta.nome.toLowerCase().contains(tipoFilter.nome.toLowerCase()));
        }
        return where;
    }
}
