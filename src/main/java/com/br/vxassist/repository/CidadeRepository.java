package com.br.vxassist.repository;

import com.br.vxassist.model.Cidade;
import com.br.vxassist.projection.CidadeProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CidadeProjection.class, path = "cidade")
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {
}
