package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.mapper.TipoDespesaMapper;
import com.br.vxassist.model.QTipoDespesa;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.TipoDespesaRepository;
import com.br.vxassist.service.TipoServiceInterface;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TipoDespesaServiceImpl implements TipoServiceInterface<TipoDespesaDTO, TipoFilter> {

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    private final TipoDespesaMapper tipoDespesaMapper = TipoDespesaMapper.INSTANCE;

    @Override
    public List<TipoDespesaDTO> get(TipoFilter tipoFilter) {
        List<TipoDespesa> tiposDespesas = new ArrayList<>();
        tipoDespesaRepository.findAll(this.getTipoDespesaPredicate(tipoFilter), Sort.by("nome").ascending()).forEach(tiposDespesas::add);
        return tipoDespesaMapper.toTipoDespesaDtoList(tiposDespesas);
    }

    @Override
    public TipoDespesaDTO create(TipoDespesaDTO tipoDespesaDTO){
        return tipoDespesaMapper.toDTO(tipoDespesaRepository.save(tipoDespesaMapper.toModel(tipoDespesaDTO)));
    }

    @Override
    public void update(TipoDespesaDTO tipoDespesaDTO){
        tipoDespesaRepository.findById(tipoDespesaDTO.getId()).orElseThrow(EntityNotFoundException::new);
        tipoDespesaRepository.save(tipoDespesaMapper.toModel(tipoDespesaDTO));
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
