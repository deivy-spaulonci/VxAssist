package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.exception.IdNotFound;
import com.br.vxassist.mapper.TipoDespesaMapper;
import com.br.vxassist.model.FormaPagamento;
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

    private final TipoDespesaMapper tipoDespesaMapper = TipoDespesaMapper.INSTANCE;

    @Override
    public List<TipoDespesaDTO> get() {
        return tipoDespesaMapper.toTipoDespesaDtoList(tipoDespesaRepository.findAll(Sort.by("nome").ascending()));
    }

    @Override
    public TipoDespesaDTO save(TipoDespesaDTO tipoDespesaDTO){
        return tipoDespesaMapper.toDTO(tipoDespesaRepository.save(tipoDespesaMapper.toModel(tipoDespesaDTO)));
    }

    @Override
    public TipoDespesaDTO findTipoDespesaById(Long id) {
        TipoDespesa tipoDespesa = this.tipoDespesaRepository.findById(id).orElseThrow(IdNotFound::new);
        return tipoDespesaMapper.toDTO(tipoDespesa);
    }
}
