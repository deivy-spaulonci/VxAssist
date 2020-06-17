package com.br.vxassist.serviceImpl;

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
    private ExtratoDescricaoRepository ExtratoDescricaoRepository;

    @Override
    public List<ExtratoDescricao> getAll(Pageable pageable) {
        return ExtratoDescricaoRepository.findAll(pageable).getContent();
    }

    @Override
    public ExtratoDescricao save(ExtratoDescricao extratoDescricao){
        return ExtratoDescricaoRepository.save(extratoDescricao);
    }

}
