package com.br.vxassist.mapper;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.model.FormaPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FormaPagamentoMapper {
    FormaPagamentoMapper INSTANCE = Mappers.getMapper(FormaPagamentoMapper.class);

    FormaPagamento toModel(FormaPagamentoDTO formaPagamentoDTO);

    FormaPagamentoDTO toDTO(FormaPagamento formaPagamento);

    List<FormaPagamentoDTO> toFormaPagamentoDtoList(List<FormaPagamento> formasPagamento);
}
