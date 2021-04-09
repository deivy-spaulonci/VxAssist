package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.TipoContaDTO;
import com.br.vxassist.exception.IdNotFound;
import com.br.vxassist.mapper.TipoContaMapper;
import com.br.vxassist.model.FormaPagamento;
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

    private final TipoContaMapper tipoContaMapper = TipoContaMapper.INSTANCE;

    @Override
    public List<TipoContaDTO> get() {
        return tipoContaMapper.toTipoContaDtoList(tipoContaRepository.findAll(Sort.by("nome").ascending()));
    }

    @Override
    public TipoContaDTO save(TipoContaDTO tipoContaDTO){
        return tipoContaMapper.toDTO(tipoContaRepository.save(tipoContaMapper.toModel(tipoContaDTO)));
    }

    @Override
    public TipoContaDTO findTipoContaById(Long id) {
        TipoConta tipoConta = this.tipoContaRepository.findById(id).orElseThrow(IdNotFound::new);
        return tipoContaMapper.toDTO(tipoConta);
    }

}
