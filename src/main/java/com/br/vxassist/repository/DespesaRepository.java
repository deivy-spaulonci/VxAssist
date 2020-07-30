package com.br.vxassist.repository;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.QDespesa;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(excerptProjection = DespesaProjection.class, path = "despesa")
@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>,
        QuerydslPredicateExecutor<Despesa>, QuerydslBinderCustomizer<QDespesa> {
    @Override
    default public void customize(QuerydslBindings bindings, QDespesa root) {

        bindings.bind(root.id).first(NumberExpression::eq);
        bindings.bind(root.tipoDespesa.id).first(NumberExpression::eq);
        bindings.bind(root.fornecedor.id).first(NumberExpression::eq);
        bindings.bind(root.formaPagamento.id).first(NumberExpression::eq);
        bindings.bind(root.data).all((path, value) -> {
            List<? extends Date> dates = new ArrayList<>(value);
            if (dates.size() == 1) {
                return Optional.of(path.eq(dates.get(0)));
            } else {
                Date from = dates.get(0);
                Date to = dates.get(1);
                return Optional.of(path.between(from, to));
            }
        });

    }

}
