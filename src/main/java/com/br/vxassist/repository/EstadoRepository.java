package com.br.vxassist.repository;


import com.br.vxassist.model.Estado;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Estado", path = "estado")
public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {
}
