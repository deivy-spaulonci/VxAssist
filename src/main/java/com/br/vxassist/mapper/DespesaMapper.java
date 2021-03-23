package com.br.vxassist.mapper;

import com.br.vxassist.dto.DespesaDTO;
import com.br.vxassist.model.Despesa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DespesaMapper {

    DespesaMapper INSTANCE = Mappers.getMapper(DespesaMapper.class);

    Despesa toModel(DespesaDTO bookDTO);

    DespesaDTO toDTO(Despesa despesa);

    List<DespesaDTO> toDespesaDtoList(List<Despesa> despesas);

}
