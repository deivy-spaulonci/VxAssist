package com.br.vxassist.repository;

import com.br.vxassist.model.Extrato;
import com.br.vxassist.projection.ExtratoProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = ExtratoProjection.class, path = "extrato")
public interface ExtratoRepository extends PagingAndSortingRepository<Extrato, Long> {
}
