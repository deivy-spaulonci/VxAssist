package com.br.vxassist.repository;

import com.br.vxassist.model.TipoConta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "TipoConta", path = "tipoConta")
public interface TipoContaRepository extends PagingAndSortingRepository<TipoConta, Long> {
}
