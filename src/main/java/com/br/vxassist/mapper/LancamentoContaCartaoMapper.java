package com.br.vxassist.mapper;

import com.br.vxassist.dto.LancamentoContaCartaoDTO;
import com.br.vxassist.model.LancamentoContaCartao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LancamentoContaCartaoMapper {
    LancamentoContaCartaoMapper INSTANCE = Mappers.getMapper(LancamentoContaCartaoMapper.class);

    LancamentoContaCartao toModel(LancamentoContaCartaoDTO lancamentoContaCartaoDTO);

    LancamentoContaCartaoDTO toDTO(LancamentoContaCartao lancamentoContaCartao);

    List<LancamentoContaCartaoDTO> toLancamentoContaCartaoaDtoList(List<LancamentoContaCartao> lancamentosContaCartao);
}
