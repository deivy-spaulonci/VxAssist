package com.br.vxassist.repository;

import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.QDespesa;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

//@RepositoryRestResource(excerptProjection = DespesaProjection.class, path = "despesa")
@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>,
        QuerydslPredicateExecutor<Despesa> {

//    @Query("SELECT d FROM Despesa d WHERE c.movie = :movie")
//    List<Despesa> findByMovieCustom(@Param("movie") String movieName, Pageable pageable);

//    @Query("FROM Customer c " +
//            "WHERE LOWER(c.name) like %:searchTerm% " +
//            "OR LOWER(c.email) like %:searchTerm%")
//    Page<Customer> search(
//            @Param("searchTerm") String searchTerm,
//            Pageable pageable);
}
