package com.br.vxassist.mapper;

import com.br.vxassist.dto.TipoContaDTO;
import com.br.vxassist.model.TipoConta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TipoContaMapper {
    TipoContaMapper INSTANCE = Mappers.getMapper(TipoContaMapper.class);

    TipoConta toModel(TipoContaDTO tipoContaDTO);

    TipoContaDTO toDTO(TipoConta tipoConta);

    List<TipoContaDTO> toTipoContaDtoList(List<TipoConta> tiposConta);

}
