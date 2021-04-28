package com.br.vxassist.postgres.model;

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
@Table(name = "TIPO_INFORMACAO_EXTRA")
public class TipoInformacaoExtra implements Serializable {

    @Id
    @TableGenerator(name = "TIPO_INFORMACAO_EXTRA_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "tipo_informacao_extra_id",
            allocationSize = 1,
            initialValue = 35,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TIPO_INFORMACAO_EXTRA_IDS")
    private Long id;

    @Size(min = 3, message = "{size.tipo-informacao-extra.nome}")
    @NotBlank(message = "{notblank.tipo-informacao-extra.nome}")
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
        TipoInformacaoExtra other = (TipoInformacaoExtra) obj;
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
