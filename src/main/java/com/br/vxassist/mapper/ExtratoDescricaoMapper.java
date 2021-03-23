package com.br.vxassist.mapper;

import com.br.vxassist.dto.ExtratoDescricaoDTO;
import com.br.vxassist.model.ExtratoDescricao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExtratoDescricaoMapper {
    ExtratoDescricaoMapper INSTANCE = Mappers.getMapper(ExtratoDescricaoMapper.class);

    ExtratoDescricao toModel(ExtratoDescricaoDTO extratoDescricaoDTO);

    ExtratoDescricaoDTO toDTO(ExtratoDescricao extratoDescricao);

    List<ExtratoDescricaoDTO> toExtratoDescricaoDtoList(List<ExtratoDescricao> extratosDescricao);

}
