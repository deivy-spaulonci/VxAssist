package com.br.vxassist.repository;

import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.projection.TipoDespesaProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = TipoDespesaProjection.class, path = "tipo-despesa")
public interface TipoDespesaRepository extends PagingAndSortingRepository<TipoDespesa, Long> {
}
