package com.br.vxassist.repository;

import com.br.vxassist.model.TipoConta;
import com.br.vxassist.projection.TipoContaProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = TipoContaProjection.class, path = "tipo-conta")
public interface TipoContaRepository extends PagingAndSortingRepository<TipoConta, Long> {
}
