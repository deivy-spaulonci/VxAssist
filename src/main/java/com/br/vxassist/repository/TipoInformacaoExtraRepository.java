package com.br.vxassist.repository;

import com.br.vxassist.model.TipoInformacaoExtra;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "TipoInformacaoExtra", path = "tipoInformacaoExtra")
public interface TipoInformacaoExtraRepository extends PagingAndSortingRepository<TipoInformacaoExtra, Long> {
}

