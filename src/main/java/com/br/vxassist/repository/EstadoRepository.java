package com.br.vxassist.repository;


import com.br.vxassist.model.Conta;
import com.br.vxassist.model.Estado;
import com.br.vxassist.model.QConta;
import com.br.vxassist.model.QEstado;
import com.br.vxassist.projection.EstadoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>,
        QuerydslPredicateExecutor<Estado>, QuerydslBinderCustomizer<QEstado> {

    @Override
    default public void customize(QuerydslBindings bindings, QEstado root) {
    }
}
