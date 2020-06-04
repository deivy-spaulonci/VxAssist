package com.br.vxassist.repository;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.projection.DespesaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = DespesaProjection.class, path = "despesa")
public interface DespesaRepository extends PagingAndSortingRepository<Despesa, Long> {

    @RestResource(rel = "findByOrderByTipoDespesaNome", path = "findByOrderByTipoDespesaNome")
    List<Despesa> findByOrderByTipoDespesaNome(Pageable page);


    @RestResource(rel = "findByOrderByFornecedorNome", path = "findByOrderByFornecedorNome")
    List<Despesa> findByOrderByFornecedorNome(Pageable page);

}
