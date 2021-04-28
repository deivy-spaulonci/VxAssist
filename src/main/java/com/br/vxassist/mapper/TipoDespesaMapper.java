package com.br.vxassist.mapper;

import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.model.TipoDespesa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TipoDespesaMapper {
    TipoDespesaMapper INSTANCE = Mappers.getMapper(TipoDespesaMapper.class);

    TipoDespesa toModel(TipoDespesaDTO tipoDespesaDTO);

    TipoDespesaDTO toDTO(TipoDespesa tipoDespesa);

    List<TipoDespesaDTO> toTipoDespesaDtoList(List<TipoDespesa> tiposDespesa);
}
