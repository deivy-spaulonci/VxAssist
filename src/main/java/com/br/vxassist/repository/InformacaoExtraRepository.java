package com.br.vxassist.repository;

import com.br.vxassist.model.InformacaoExtra;
import com.br.vxassist.projection.InformacaoExtraProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = InformacaoExtraProjection.class, path = "informacao-extra")
public interface InformacaoExtraRepository extends PagingAndSortingRepository<InformacaoExtra, Long> {
}

