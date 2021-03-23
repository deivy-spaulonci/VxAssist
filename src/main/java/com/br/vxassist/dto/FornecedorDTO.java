package com.br.vxassist.dto;

import com.br.vxassist.model.Cidade;
import com.br.vxassist.model.TipoDespesa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
    private Long id;

    @Size(min = 3, message = "{size.fornecedor.nome}")
    @NotBlank(message = "{notblank.fornecedor.nome}")
    private String nome;

    @Size(min = 3, message = "{size.fornecedor.razao-social}")
    @NotBlank(message = "{notblank.fornecedor.razao-social}")
    private String razaoSocial;

    private String cnpj;

    private String inscricaoEstadual;

    private String endereco;

    private String bairro;

    private String complemento;

    private String cep;

    private String telefone;

    private Cidade cidade;

    private List<TipoDespesa> tipoDespesas;
}
