package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.Estado;
import com.br.vxassist.repository.EstadoRepository;
import com.br.vxassist.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Estado> getAll() {
        return estadoRepository.findAll(Sort.by("nome").ascending());
    }
}
