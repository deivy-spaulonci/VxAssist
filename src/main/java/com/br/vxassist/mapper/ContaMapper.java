package com.br.vxassist.mapper;

import com.br.vxassist.dto.ContaDTO;
import com.br.vxassist.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContaMapper {
    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    Conta toModel(ContaDTO contaDTO);

    ContaDTO toDTO(Conta conta);

    List<ContaDTO> toContaDtoList(List<Conta> contas);
}
