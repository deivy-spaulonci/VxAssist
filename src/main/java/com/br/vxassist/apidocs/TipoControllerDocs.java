package com.br.vxassist.apidocs;

import com.br.vxassist.dto.TipoDespesaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Tipo gerencimaneto")
public interface TipoControllerDocs<D> {

    @ApiOperation(value = "Operação de criação de tipo")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Sucesso na criação do tipo"),
        @ApiResponse(code = 400, message = "Campos obrigatórios requeridos, ou tipo ja cadastrado com esse nome!")
    })
    TipoDespesaDTO create(D d);
}
