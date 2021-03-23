package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.TipoInformacaoExtraDTO;
import com.br.vxassist.mapper.TipoInformacaoExtraMapper;
import com.br.vxassist.repository.TipoInformacaoExtraRepository;
import com.br.vxassist.service.TipoInformacaoExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TipoInformacaoExtraServiceImpl implements TipoInformacaoExtraService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TipoInformacaoExtraRepository tipoInformacaoExtraRepository;

    private final TipoInformacaoExtraMapper tipoInformacaoExtraMapper = TipoInformacaoExtraMapper.INSTANCE;

    @Override
    public List<TipoInformacaoExtraDTO> get() {
        return tipoInformacaoExtraMapper.toTipoInformacaoExtraDtoList(tipoInformacaoExtraRepository.findAll(Sort.by("nome").ascending()));
    }

}
