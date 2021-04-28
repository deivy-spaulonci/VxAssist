package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.InformacaoExtraDTO;
import com.br.vxassist.mapper.InformacaoExtraMapper;
import com.br.vxassist.repository.InformacaoExtraRepository;
import com.br.vxassist.service.InformacaoExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class InformacaoExtraServiceImpl implements InformacaoExtraService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private InformacaoExtraRepository informacaoExtraRepository;

    private final InformacaoExtraMapper informacaoExtraMapper = InformacaoExtraMapper.INSTANCE;

    @Override
    public InformacaoExtraDTO save(InformacaoExtraDTO informacaoExtraDTO){
        return informacaoExtraMapper.toDTO(informacaoExtraRepository.save(informacaoExtraMapper.toModel(informacaoExtraDTO)));
    }

}
