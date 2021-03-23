package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.ExtratoDescricaoDTO;
import com.br.vxassist.mapper.ExtratoDescricaoMapper;
import com.br.vxassist.model.ExtratoDescricao;
import com.br.vxassist.model.ExtratoDescricao;
import com.br.vxassist.repository.ExtratoDescricaoRepository;
import com.br.vxassist.service.ExtratoDescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ExtratoDescricaoServiceImpl implements ExtratoDescricaoService {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ExtratoDescricaoRepository extratoDescricaoRepository;

    private final ExtratoDescricaoMapper extratoDescricaoMapper = ExtratoDescricaoMapper.INSTANCE;

    @Override
    public List<ExtratoDescricaoDTO> get(Pageable pageable) {
        return extratoDescricaoMapper.toExtratoDescricaoDtoList(extratoDescricaoRepository.findAll(pageable).getContent());
    }

    @Override
    public ExtratoDescricaoDTO save(ExtratoDescricaoDTO extratoDescricaoDTO){
        return extratoDescricaoMapper.toDTO(extratoDescricaoRepository.save(extratoDescricaoMapper.toModel(extratoDescricaoDTO)));
    }

}
