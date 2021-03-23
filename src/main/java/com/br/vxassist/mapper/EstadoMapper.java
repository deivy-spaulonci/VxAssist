package com.br.vxassist.mapper;

import com.br.vxassist.dto.EstadoDTO;
import com.br.vxassist.model.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EstadoMapper {
    EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

    Estado toModel(EstadoDTO estadoDTO);

    EstadoDTO toDTO(Estado estado);

    List<EstadoDTO> toEstadoDtoList(List<Estado> estados);
}
