package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.TipoInformacaoExtra;
import com.br.vxassist.repository.TipoInformacaoExtraRepository;
import com.br.vxassist.service.TipoInformacaoExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TipoInformacaoExtraServiceImpl implements TipoInformacaoExtraService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TipoInformacaoExtraRepository tipoInformacaoExtraRepository;

    @Override
    public List<TipoInformacaoExtra> getAll(Pageable pageable) {
        return tipoInformacaoExtraRepository.findAll(pageable).getContent();
    }

}
