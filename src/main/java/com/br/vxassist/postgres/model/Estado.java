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
@Table(name = "ESTADO")
public class Estado implements Serializable {

    @Id
    @TableGenerator(name = "ESTADO_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "estado_id",
            allocationSize = 1,
            initialValue = 28,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ESTADO_IDS")
    private Long id;

    @NotBlank(message = "{nobblank.estado.nome}")
    @Column(length = 60, nullable = false)
    private String nome;

    @Size(min = 2, max = 2, message = "{size.estado.sigla}")
    @NotBlank(message = "{notblank.estado.sigla}")
    @Column(length = 2, nullable = false)
    private String sigla;

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
        Estado other = (Estado) obj;
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
