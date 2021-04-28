package com.br.vxassist.mapper;

import com.br.vxassist.dto.InformacaoExtraDTO;
import com.br.vxassist.model.InformacaoExtra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InformacaoExtraMapper {
    InformacaoExtraMapper INSTANCE = Mappers.getMapper(InformacaoExtraMapper.class);

    InformacaoExtra toModel(InformacaoExtraDTO contaDTO);

    InformacaoExtraDTO toDTO(InformacaoExtra InformacaoExtra);

    List<InformacaoExtraDTO> toInformacaoExtraDtoList(List<InformacaoExtra> informacoesExtra);
}
