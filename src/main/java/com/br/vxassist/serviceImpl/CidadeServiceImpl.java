package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.CidadeDTO;
import com.br.vxassist.filter.CidadeFilter;
import com.br.vxassist.mapper.CidadeMapper;
import com.br.vxassist.model.Cidade;
import com.br.vxassist.model.QCidade;
import com.br.vxassist.repository.CidadeRepository;
import com.br.vxassist.service.ServiceInterface;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CidadeServiceImpl implements ServiceInterface<CidadeDTO, CidadeFilter> {

    @Autowired
    private CidadeRepository cidadeRepository;

    private final CidadeMapper cidadeMapper = CidadeMapper.INSTANCE;

    @Override
    public List<CidadeDTO> get(CidadeFilter cidadeFilter, Sort sort){
        List<Cidade> cidades = new ArrayList<>();
        cidadeRepository.findAll(getCidadePredicate(cidadeFilter), Sort.by("nome").ascending()).forEach(cidades::add);
        return cidadeMapper.toCidadeDTOtoList(cidades);
    }

    @Override
    public Page<CidadeDTO> getPage(CidadeFilter filter, Pageable peageble) {
        return null;
    }

    @Override
    public CidadeDTO create(CidadeDTO dto) {
        return null;
    }

    @Override
    public Long count() {
        return cidadeRepository.count();
    }

    @Override
    public CidadeDTO findById(Long id) {
        return cidadeMapper.toDTO(cidadeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void excluir(Long id) {
        return;
    }

    public BooleanBuilder getCidadePredicate(CidadeFilter cidadeFilter){
        QCidade qCidade = QCidade.cidade;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(cidadeFilter.id)){
            where.and(qCidade.id.eq(cidadeFilter.id));
        }

        if(Objects.nonNull(cidadeFilter.nome) && !cidadeFilter.nome.trim().isEmpty()){
            where.and(qCidade.nome.likeIgnoreCase("%"+cidadeFilter.nome.toLowerCase()+"%"));
        }

        if(Objects.nonNull(cidadeFilter.estado)){
            where.and(qCidade.estado.eq(cidadeFilter.estado));
        }
        return where;
    }



}
