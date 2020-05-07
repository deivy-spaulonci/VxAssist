package com.br.vxassist.repository;


import com.br.vxassist.model.Estado;
import com.br.vxassist.projection.EstadoProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = EstadoProjection.class, path = "estado")
public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {
}
