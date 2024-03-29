package com.br.vxassist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FORMA_PAGAMENTO")
public class FormaPagamento implements Serializable {

    @Id
    @TableGenerator(name = "FORMA_PAGAMENTO_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "forma_pagamento_id",
            allocationSize = 1,
            initialValue = 14,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FORMA_PAGAMENTO_IDS")
    private Long id;

    @Size(min = 3, message = "{size.forma-pagamento.nome}")
    @NotBlank(message = "{notblank.forma-pagamento.nome}")
    @Column(length = 60, nullable = false)
    private String nome;

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
