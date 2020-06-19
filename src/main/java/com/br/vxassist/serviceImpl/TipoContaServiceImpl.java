package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.TipoConta;
import com.br.vxassist.repository.TipoContaRepository;
import com.br.vxassist.service.TipoContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TipoContaServiceImpl implements TipoContaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TipoContaRepository tipoContaRepository;

    @Override
    public List<TipoConta> getAllSelect() {
        return tipoContaRepository.findAll(Sort.by("nome").ascending());
    }

}
