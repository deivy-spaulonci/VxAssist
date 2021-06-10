package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.InformacaoExtraDTO;
import com.br.vxassist.mapper.InformacaoExtraMapper;
import com.br.vxassist.repository.InformacaoExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformacaoExtraServiceImpl {

    @Autowired
    private InformacaoExtraRepository informacaoExtraRepository;

    private final InformacaoExtraMapper informacaoExtraMapper = InformacaoExtraMapper.INSTANCE;

    public InformacaoExtraDTO save(InformacaoExtraDTO informacaoExtraDTO){
        return informacaoExtraMapper.toDTO(informacaoExtraRepository.save(informacaoExtraMapper.toModel(informacaoExtraDTO)));
    }

}
