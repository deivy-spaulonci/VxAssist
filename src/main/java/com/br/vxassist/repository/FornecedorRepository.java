package com.br.vxassist.repository;

import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.QFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>,
        QuerydslPredicateExecutor<Fornecedor>, QuerydslBinderCustomizer<QFornecedor> {

    @Override
    default public void customize(QuerydslBindings bindings, QFornecedor root) {
    }



}

