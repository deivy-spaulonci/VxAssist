package com.br.vxassist.repository;

import com.br.vxassist.model.TipoDespesa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tipoDespesa", path = "tipoDespesa")
public interface TipoDespesaRepository extends PagingAndSortingRepository<TipoDespesa, Long> {
}
