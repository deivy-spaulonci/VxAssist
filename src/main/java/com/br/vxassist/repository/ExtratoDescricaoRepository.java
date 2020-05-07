package com.br.vxassist.repository;

import com.br.vxassist.model.ExtratoDescricao;
import com.br.vxassist.projection.ExtratoDescricaoProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = ExtratoDescricaoProjection.class, path = "extrato-descricao")
public interface ExtratoDescricaoRepository extends PagingAndSortingRepository<ExtratoDescricao, Long> {
}
