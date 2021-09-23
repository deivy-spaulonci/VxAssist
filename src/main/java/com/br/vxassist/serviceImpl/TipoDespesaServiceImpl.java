package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.exception.TipoAlreadyExistsException;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.mapper.TipoDespesaMapper;
import com.br.vxassist.model.QTipoDespesa;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.TipoDespesaRepository;
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
public class TipoDespesaServiceImpl implements ServiceInterface<TipoDespesaDTO, TipoFilter> {

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    private final TipoDespesaMapper tipoDespesaMapper = TipoDespesaMapper.INSTANCE;

    @Override
    public List<TipoDespesaDTO> get(TipoFilter tipoFilter, Sort sort) {
        List<TipoDespesa> tiposDespesas = new ArrayList<>();
        tipoDespesaRepository.findAll(this.getTipoDespesaPredicate(tipoFilter), sort).forEach(tiposDespesas::add);
        return tipoDespesaMapper.toTipoDespesaDtoList(tiposDespesas);
    }

    @Override
    public Page<TipoDespesaDTO> getPage(TipoFilter filter, Pageable peageble) {
        return null;
    }

    @Override
    public TipoDespesaDTO create(TipoDespesaDTO tipoDespesaDTO){
        if(Objects.nonNull(tipoDespesaRepository.findById(tipoDespesaDTO.getId()).get())){
            throw new TipoAlreadyExistsException(tipoDespesaDTO.getId().toString());
        }
        return tipoDespesaMapper.toDTO(tipoDespesaRepository.save(tipoDespesaMapper.toModel(tipoDespesaDTO)));
    }

    @Override
    public void update(TipoDespesaDTO tipoDespesaDTO){
        tipoDespesaRepository.findById(tipoDespesaDTO.getId()).orElseThrow(EntityNotFoundException::new);
        tipoDespesaRepository.save(tipoDespesaMapper.toModel(tipoDespesaDTO));
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public TipoDespesaDTO findById(Long id) {
        return null;
    }

    @Override
    public void excluir(Long id) {
    }

    public BooleanBuilder getTipoDespesaPredicate(TipoFilter tipoFilter){
        QTipoDespesa qTipoDespesa = QTipoDespesa.tipoDespesa;
        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(tipoFilter.id)){
            where.and(qTipoDespesa.id.stringValue().eq(tipoFilter.id.toString()));
        }
        if(Objects.nonNull(tipoFilter.nome) && !tipoFilter.nome.trim().isEmpty()){
            where.and(qTipoDespesa.nome.toLowerCase().contains(tipoFilter.nome.toLowerCase()));
        }
        return where;
    }
}
