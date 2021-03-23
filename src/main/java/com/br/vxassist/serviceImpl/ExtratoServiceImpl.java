package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.ExtratoDTO;
import com.br.vxassist.mapper.ExtratoMapper;
import com.br.vxassist.model.Extrato;
import com.br.vxassist.repository.ExtratoRepository;
import com.br.vxassist.service.ExtratoService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ExtratoServiceImpl implements ExtratoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ExtratoRepository extratoRepository;

    private final ExtratoMapper extratoMapper = ExtratoMapper.INSTANCE;

    @Override
    public Page<ExtratoDTO> getPage(Predicate predicate, Pageable pageable) {
        return extratoRepository.findAll(predicate, pageable).map(extratoMapper::toDTO);
    }

    @Override
    public ExtratoDTO save(ExtratoDTO extratoDTO) {
        return extratoMapper.toDTO(extratoRepository.save(extratoMapper.toModel(extratoDTO)));
    }

}
