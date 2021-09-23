package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.TipoInformacaoExtraDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.mapper.TipoInformacaoExtraMapper;
import com.br.vxassist.model.QTipoInformacaoExtra;
import com.br.vxassist.model.TipoInformacaoExtra;
import com.br.vxassist.repository.TipoInformacaoExtraRepository;
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
public class TipoInformacaoExtraServiceImpl implements ServiceInterface<TipoInformacaoExtraDTO, TipoFilter> {

    @Autowired
    private TipoInformacaoExtraRepository tipoInformacaoExtraRepository;

    private final TipoInformacaoExtraMapper tipoInformacaoExtraMapper = TipoInformacaoExtraMapper.INSTANCE;

    @Override
    public List<TipoInformacaoExtraDTO> get(TipoFilter filter, Sort sort) {
        List<TipoInformacaoExtra> tiposInformacaoExtra = new ArrayList<>();
        tipoInformacaoExtraRepository.findAll(getTipoInformacaoExtraPredicate(filter), sort).forEach(tiposInformacaoExtra::add);
        return tipoInformacaoExtraMapper.toTipoInformacaoExtraDtoList(tiposInformacaoExtra);
    }

    @Override
    public Page<TipoInformacaoExtraDTO> getPage(TipoFilter filter, Pageable peageble) {
        return null;
    }

    @Override
    public TipoInformacaoExtraDTO create(TipoInformacaoExtraDTO tipoInformacaoExtraDTO){
        return tipoInformacaoExtraMapper.toDTO(tipoInformacaoExtraRepository.save(tipoInformacaoExtraMapper.toModel(tipoInformacaoExtraDTO)));
    }

    @Override
    public void update(TipoInformacaoExtraDTO tipoInformacaoExtraDTO) {
        tipoInformacaoExtraRepository.findById(tipoInformacaoExtraDTO.getId()).orElseThrow(EntityNotFoundException::new);
        tipoInformacaoExtraRepository.save(tipoInformacaoExtraMapper.toModel(tipoInformacaoExtraDTO));
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public TipoInformacaoExtraDTO findById(Long id) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }

    private BooleanBuilder getTipoInformacaoExtraPredicate(TipoFilter tipoFilter){
        QTipoInformacaoExtra qTipoInformacaoExtra = QTipoInformacaoExtra.tipoInformacaoExtra;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(tipoFilter)){
            if(Objects.nonNull(tipoFilter.id)){
                where.and(qTipoInformacaoExtra.id.stringValue().contains(tipoFilter.id.toString()));
            }
            if(Objects.nonNull(tipoFilter.nome) && Strings.isNotEmpty(tipoFilter.nome)){
                where.and(qTipoInformacaoExtra.nome.toLowerCase().likeIgnoreCase('%' + tipoFilter.nome.toLowerCase() + '%'));
            }
        }
        return where;
    }
}
