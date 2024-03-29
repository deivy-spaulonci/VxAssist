package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.DespesaDTO;
import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.mapper.DespesaMapper;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.QDespesa;
import com.br.vxassist.repository.DespesaRepository;
import com.br.vxassist.service.ServiceInterface;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DespesaServiceImpl implements ServiceInterface<DespesaDTO, DespesaFilter> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DespesaRepository despesaRepository;

    private final DespesaMapper despesaMapper = DespesaMapper.INSTANCE;

    @Override
    public List<DespesaDTO> get(DespesaFilter despesaFilter, Sort sort){
        List<Despesa> despesas = new ArrayList<>();
        despesaRepository.findAll(this.getDespesaPredicate(despesaFilter), sort).forEach(despesas::add);
        return despesaMapper.toDespesaDTOtoList(despesas);
    }

    @Override
    public Page<DespesaDTO> getPage(DespesaFilter despesaFilter, Pageable pageable){
        return despesaRepository.findAll(this.getDespesaPredicate(despesaFilter), pageable).map(despesaMapper::toDTO);
    }

    @Override
    public DespesaDTO create(DespesaDTO despesaDTO){
        return despesaMapper.toDTO(despesaRepository.save(despesaMapper.toModel(despesaDTO)));
    }

    @Override
    public void update(DespesaDTO dto) {

    }

    @Override
    public Long count() {
        return despesaRepository.count();
    }

    @Override
    public DespesaDTO findById(Long id) {
        Despesa despesa = this.despesaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return despesaMapper.toDTO(despesa);
    }

    @Override
    public void excluir(Long id) {
        this.despesaRepository.deleteById(id);
    }

    public BigDecimal total(DespesaFilter despesaFilter) {
        JPAQuery<BigDecimal> query = new JPAQuery<>(this.entityManager);
        QDespesa qDespesa = QDespesa.despesa;

        query.select(qDespesa.valor.sum()).from(qDespesa);

        if(Objects.nonNull(despesaFilter.getId())){
            query.where(qDespesa.id.like(despesaFilter.getId().toString()));
        }
        if(Objects.nonNull(despesaFilter.getTipoDespesa()) && Objects.nonNull(despesaFilter.tipoDespesa.getId())){
            query.where(qDespesa.tipoDespesa.id.eq(despesaFilter.getTipoDespesa().getId()));
        }
        if(Objects.nonNull(despesaFilter.getFornecedor())){
            if(Objects.nonNull(despesaFilter.fornecedor.getId())){
                query.where(qDespesa.fornecedor.id.eq(despesaFilter.getFornecedor().getId()));
            }
            if(Objects.nonNull(despesaFilter.fornecedor.getNome()) && !despesaFilter.fornecedor.getNome().trim().isEmpty()){
                query.where(qDespesa.fornecedor.nome.toLowerCase().likeIgnoreCase(despesaFilter.fornecedor.getNome().toLowerCase())
                        .or(qDespesa.fornecedor.razaoSocial.toLowerCase().likeIgnoreCase(despesaFilter.fornecedor.getNome().toLowerCase())));

            }
        }
        if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            query.where(qDespesa.data.between(despesaFilter.getDataInicial(), despesaFilter.getDataFinal()));
        }else if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.isNull(despesaFilter.getDataFinal())){
            query.where(qDespesa.data.goe(despesaFilter.getDataInicial()));
        }else if(Objects.isNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            query.where(qDespesa.data.loe(despesaFilter.getDataFinal()));
        }
        if(Objects.nonNull(despesaFilter.getFormaPagamento()) && Objects.nonNull(despesaFilter.getFormaPagamento().getId())){
            query.where(qDespesa.formaPagamento.id.eq(despesaFilter.getFormaPagamento().getId()));
        }
        return query.fetch().get(0);
    }

    public BooleanBuilder getDespesaPredicate(DespesaFilter despesaFilter){
        QDespesa qDespesa = QDespesa.despesa;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(despesaFilter.id)){
            //where.and(qDespesa.id.eq(despesaFilter.id));
            where.and(qDespesa.id.stringValue().contains(despesaFilter.id.toString()));
        }
        if(Objects.nonNull(despesaFilter.tipoDespesa) && Objects.nonNull(despesaFilter.tipoDespesa.getId())){
            where.and(qDespesa.tipoDespesa.eq(despesaFilter.tipoDespesa));
        }
        if(Objects.nonNull(despesaFilter.getFornecedor())){
            if(Objects.nonNull(despesaFilter.fornecedor.getId())){
                where.and(qDespesa.fornecedor.id.eq(despesaFilter.getFornecedor().getId()));
            }
            if(Objects.nonNull(despesaFilter.fornecedor.getNome()) && !despesaFilter.fornecedor.getNome().trim().isEmpty()){
                where.and(qDespesa.fornecedor.nome.toLowerCase().likeIgnoreCase('%'+despesaFilter.fornecedor.getNome().toLowerCase()+'%'));
                where.or(qDespesa.fornecedor.razaoSocial.toLowerCase().likeIgnoreCase('%'+despesaFilter.fornecedor.getNome().toLowerCase()+'%'));
            }
        }
        if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            where.and(qDespesa.data.between(despesaFilter.getDataInicial(), despesaFilter.getDataFinal()));
        }else if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.isNull(despesaFilter.getDataFinal())){
            where.and(qDespesa.data.goe(despesaFilter.getDataInicial()));
        }else if(Objects.isNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            where.and(qDespesa.data.loe(despesaFilter.getDataFinal()));
        }
        if(Objects.nonNull(despesaFilter.getFormaPagamento()) && Objects.nonNull(despesaFilter.getFormaPagamento().getId())){
            where.and(qDespesa.formaPagamento.id.eq(despesaFilter.getFormaPagamento().getId()));
        }

        return where;
    }
}
