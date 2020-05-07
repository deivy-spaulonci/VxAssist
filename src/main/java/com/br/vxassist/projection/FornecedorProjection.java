package com.br.vxassist.projection;

import com.br.vxassist.model.Cidade;
import com.br.vxassist.model.Fornecedor;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fornecedorProjection", types = {Fornecedor.class})
public interface FornecedorProjection {
    Long getId();
    String getNome();
    String getRazaoSocial();
    String getCnpj();
    String getInscricaoEstadual();
    String getEndereco();
    String getBairro();
    String getComplemento();
    String getCep();
    String getTelefone();
    Cidade getCidade();
}
