package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.InformacaoExtra;
import com.br.vxassist.repository.InformacaoExtraRepository;
import com.br.vxassist.service.InformacaoExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class InformacaoExtraServiceImpl implements InformacaoExtraService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private InformacaoExtraRepository informacaoExtraRepository;

    @Override
    public InformacaoExtra save(InformacaoExtra informacaoExtra){
        return informacaoExtraRepository.save(informacaoExtra);
    }

}
