package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.ContaDTO;
import com.br.vxassist.exception.IdNotFound;
import com.br.vxassist.filter.ContaFilter;
import com.br.vxassist.mapper.ContaMapper;
import com.br.vxassist.mapper.DespesaMapper;
import com.br.vxassist.model.Conta;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.QConta;
import com.br.vxassist.repository.ContaRepository;
import com.br.vxassist.service.ContaService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ContaServiceImpl implements ContaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContaRepository contaRepository;

    private final ContaMapper contaMapper = ContaMapper.INSTANCE;

    @Override
    public List<ContaDTO> get(ContaFilter contaFilter, Sort sort){
        List<Conta> contas = new ArrayList<>();
        contaRepository.findAll(this.getContaPredicate(contaFilter), sort).forEach(contas::add);
        return contaMapper.toContaDtoList(contas);
    }

    @Override
    public Page<ContaDTO> getPage(ContaFilter contaFilter, Pageable pageable) {
        return contaRepository.findAll(this.getContaPredicate(contaFilter), pageable).map(contaMapper::toDTO);
    }

    public BooleanBuilder getContaPredicate(ContaFilter contaFilter){
        QConta qConta = QConta.conta;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(contaFilter.id)){
            where.and(qConta.id.eq(contaFilter.id));
        }
        if(Objects.nonNull(contaFilter.codigoBarra)){
            where.and(qConta.codigoBarra.likeIgnoreCase(contaFilter.codigoBarra));
        }
        if(Objects.nonNull(contaFilter.tipoConta)){
            where.and(qConta.tipoConta.eq(contaFilter.tipoConta));
        }
        if(Objects.nonNull(contaFilter.getVencimentoInicial()) && Objects.nonNull(contaFilter.getVencimentoFinal())){
            where.and(qConta.vencimento.between(contaFilter.getVencimentoInicial(), contaFilter.getVencimentoFinal()));
        }else if(Objects.nonNull(contaFilter.getVencimentoInicial()) && Objects.isNull(contaFilter.getVencimentoFinal())){
            where.and(qConta.vencimento.goe(contaFilter.getVencimentoInicial()));
        }else if(Objects.isNull(contaFilter.getVencimentoInicial()) && Objects.nonNull(contaFilter.getVencimentoFinal())){
            where.and(qConta.vencimento.loe(contaFilter.getVencimentoFinal()));
        }
        if(Objects.nonNull(contaFilter.getEmissaoInicial()) && Objects.nonNull(contaFilter.getEmissaoFinal())){
            where.and(qConta.emissao.between(contaFilter.getEmissaoInicial(), contaFilter.getEmissaoFinal()));
        }else if(Objects.nonNull(contaFilter.getEmissaoInicial()) && Objects.isNull(contaFilter.getEmissaoFinal())){
            where.and(qConta.emissao.goe(contaFilter.getEmissaoInicial()));
        }else if(Objects.isNull(contaFilter.getEmissaoInicial()) && Objects.nonNull(contaFilter.getEmissaoFinal())){
            where.and(qConta.emissao.loe(contaFilter.getEmissaoFinal()));
        }
        return where;
    }

    @Override
    public ContaDTO save(ContaDTO contaDTO){
        return contaMapper.toDTO(contaRepository.save(contaMapper.toModel(contaDTO)));
    }

    @Override
    public Long count() {
        return contaRepository.count();
    }

    @Override
    public BigDecimal total(ContaFilter contaFilter) {
        JPAQuery<BigDecimal> query = new JPAQuery<>(this.entityManager);
        QConta qConta = QConta.conta;

        query.select(qConta.valor.sum()).from(qConta);

        if(Objects.nonNull(contaFilter.id)){
            query.where(qConta.id.eq(contaFilter.id));
        }
        if(Objects.nonNull(contaFilter.codigoBarra)){
            query.where(qConta.codigoBarra.likeIgnoreCase(contaFilter.codigoBarra));
        }
        if(Objects.nonNull(contaFilter.tipoConta)){
            query.where(qConta.tipoConta.eq(contaFilter.tipoConta));
        }
        if(Objects.nonNull(contaFilter.getVencimentoInicial()) && Objects.nonNull(contaFilter.getVencimentoFinal())){
            query.where(qConta.vencimento.between(contaFilter.getVencimentoInicial(), contaFilter.getVencimentoFinal()));
        }else if(Objects.nonNull(contaFilter.getVencimentoInicial()) && Objects.isNull(contaFilter.getVencimentoFinal())){
            query.where(qConta.vencimento.goe(contaFilter.getVencimentoInicial()));
        }else if(Objects.isNull(contaFilter.getVencimentoInicial()) && Objects.nonNull(contaFilter.getVencimentoFinal())){
            query.where(qConta.vencimento.loe(contaFilter.getVencimentoFinal()));
        }

        if(Objects.nonNull(contaFilter.getEmissaoInicial()) && Objects.nonNull(contaFilter.getEmissaoFinal())){
            query.where(qConta.emissao.between(contaFilter.getEmissaoInicial(), contaFilter.getEmissaoFinal()));
        }else if(Objects.nonNull(contaFilter.getEmissaoInicial()) && Objects.isNull(contaFilter.getEmissaoFinal())){
            query.where(qConta.emissao.goe(contaFilter.getEmissaoInicial()));
        }else if(Objects.isNull(contaFilter.getEmissaoInicial()) && Objects.nonNull(contaFilter.getEmissaoFinal())){
            query.where(qConta.emissao.loe(contaFilter.getEmissaoFinal()));
        }

        return query.fetch().get(0);
    }

    @Override
    public ContaDTO findContaById(Long id) {
        Conta conta = this.contaRepository.findById(id).orElseThrow(IdNotFound::new);
        return contaMapper.toDTO(conta);
    }

    @Override
    public void excluirConta(Long id) {
        this.contaRepository.deleteById(id);
    }
}
