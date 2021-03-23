package com.br.vxassist.mapper;

import com.br.vxassist.dto.TipoInformacaoExtraDTO;
import com.br.vxassist.model.TipoInformacaoExtra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TipoInformacaoExtraMapper {
    TipoInformacaoExtraMapper INSTANCE = Mappers.getMapper(TipoInformacaoExtraMapper.class);

    TipoInformacaoExtra toModel(TipoInformacaoExtraDTO tipoInformacaoExtraDTO);

    TipoInformacaoExtraDTO toDTO(TipoInformacaoExtra tipoInformacaoExtra);

    List<TipoInformacaoExtraDTO> toTipoInformacaoExtraDtoList(List<TipoInformacaoExtra> tiposInformacaoExtra);

}
