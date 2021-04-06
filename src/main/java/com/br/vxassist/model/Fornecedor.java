package com.br.vxassist.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FORNECEDOR")
public class Fornecedor implements Serializable {

    @Id
    @TableGenerator(name = "FORNECEDOR_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "fornecedor_id",
            allocationSize = 1,
            initialValue = 926,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FORNECEDOR_IDS")
    private Long id;

    @Size(min = 3, message = "{size.fornecedor.nome}")
    @NotBlank(message = "{notblank.fornecedor.nome}")
    @Column(length = 255, nullable = false)
    private String nome;

    @Size(min = 3, message = "{size.fornecedor.razao-social}")
    @NotBlank(message = "{notblank.fornecedor.razao-social}")
    @Column(name = "RAZAO_SOCIAL", length = 255, nullable = false)
    private String razaoSocial;

    @Column(length = 60, nullable = true)
    private String cnpj;

    @Column(name = "INSCRICAO_ESTADUAL", length = 60, nullable = true)
    private String inscricaoEstadual;

    @Column(length = 60, nullable = true)
    private String endereco;

    @Column(length = 60, nullable = true)
    private String bairro;

    @Column(length = 60, nullable = true)
    private String complemento;

    @Column(length = 60, nullable = true)
    private String cep;

    @Column(length = 60, nullable = true)
    private String telefone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CIDADE_ID")
    private Cidade cidade;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "FORNECEDOR_TIPO_DESPESA", joinColumns = @JoinColumn(name = "FORNECEDOR_ID"), inverseJoinColumns = @JoinColumn(name = "TIPO_DESPESA_ID"))
    private List<TipoDespesa> tipoDespesas;


    @Transient
    public Boolean containsTipoDespesa(Long idTipoDespesa) {
        for (TipoDespesa tipo : this.tipoDespesas) {
            if (tipo.getId().equals(idTipoDespesa)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj instanceof String) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nome;

    }

}
