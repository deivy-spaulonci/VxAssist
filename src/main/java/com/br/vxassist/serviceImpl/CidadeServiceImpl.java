package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.Cidade;
import com.br.vxassist.repository.CidadeRepository;
import com.br.vxassist.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public List<Cidade> getAll(Pageable pageable) {
        return cidadeRepository.findAll(pageable).getContent();
    }
}
