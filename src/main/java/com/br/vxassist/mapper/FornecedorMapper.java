package com.br.vxassist.mapper;

import com.br.vxassist.dto.FornecedorDTO;
import com.br.vxassist.model.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FornecedorMapper {
    FornecedorMapper INSTANCE = Mappers.getMapper(FornecedorMapper.class);

    Fornecedor toModel(FornecedorDTO fornecedorDTO);

    FornecedorDTO toDTO(Fornecedor fornecedor);

    List<FornecedorDTO> toFornecedorDtoList(List<Fornecedor> fornecedores);
}
