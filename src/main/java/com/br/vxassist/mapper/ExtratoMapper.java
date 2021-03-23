package com.br.vxassist.mapper;

import com.br.vxassist.dto.ExtratoDTO;
import com.br.vxassist.model.Extrato;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExtratoMapper {
    ExtratoMapper INSTANCE = Mappers.getMapper(ExtratoMapper.class);

    Extrato toModel(ExtratoDTO extratoDTO);

    ExtratoDTO toDTO(Extrato extrato);

    List<ExtratoDTO> toExtratoDtoList(List<Extrato> extratos);
}
