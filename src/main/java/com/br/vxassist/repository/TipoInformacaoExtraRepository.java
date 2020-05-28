package com.br.vxassist.repository;

import com.br.vxassist.model.TipoInformacaoExtra;
import com.br.vxassist.projection.TipoContaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = TipoContaProjection.class, path = "tipo-informacao-extra")
public interface TipoInformacaoExtraRepository extends PagingAndSortingRepository<TipoInformacaoExtra, Long>, JpaRepository<TipoInformacaoExtra, Long> {
}

