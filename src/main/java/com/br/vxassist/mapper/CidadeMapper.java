package com.br.vxassist.mapper;

import com.br.vxassist.dto.CidadeDTO;
import com.br.vxassist.model.Cidade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CidadeMapper {

    CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

    Cidade toModel(CidadeDTO cidadeDTO);

    CidadeDTO toDTO(Cidade cidade);

    List<CidadeDTO> toCidadeDTOtoList(List<Cidade> cidades);
}
