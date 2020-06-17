package com.br.vxassist.repository;

import com.br.vxassist.model.Extrato;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.QExtrato;
import com.br.vxassist.model.QFormaPagamento;
import com.br.vxassist.projection.FormaPagamentoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>,
        QuerydslPredicateExecutor<FormaPagamento>, QuerydslBinderCustomizer<QFormaPagamento> {

    @Override
    default public void customize(QuerydslBindings bindings, QFormaPagamento root) {
    }
}
