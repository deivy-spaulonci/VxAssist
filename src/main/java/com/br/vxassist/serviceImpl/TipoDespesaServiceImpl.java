package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.TipoDespesaRepository;
import com.br.vxassist.service.TipoDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TipoDespesaServiceImpl implements TipoDespesaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @Override
    public List<TipoDespesa> getAllSelect() {
        return tipoDespesaRepository.findAll(Sort.by("nome").ascending());
    }
}
