package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.TipoInformacaoExtraDTO;
import com.br.vxassist.mapper.TipoInformacaoExtraMapper;
import com.br.vxassist.repository.TipoInformacaoExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoInformacaoExtraServiceImpl {

    @Autowired
    private TipoInformacaoExtraRepository tipoInformacaoExtraRepository;

    private final TipoInformacaoExtraMapper tipoInformacaoExtraMapper = TipoInformacaoExtraMapper.INSTANCE;

    public List<TipoInformacaoExtraDTO> get() {
        return tipoInformacaoExtraMapper.toTipoInformacaoExtraDtoList(tipoInformacaoExtraRepository.findAll(Sort.by("nome").ascending()));
    }

}
