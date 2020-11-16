package com.br.vxassist.model;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Audited
@AuditTable(value="tipo_despesa_aud")
@Entity
@Table(name = "TIPO_DESPESA")
public class TipoDespesa implements Serializable {

    @Id
    @TableGenerator(name = "TIPO_DESPESA_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "tipo_despesa_id",
            allocationSize = 1,
            initialValue = 28,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TIPO_DESPESA_IDS")
    private Long id;

    @Size(min = 3, message = "{size.tipo-despesa.nome}")
    @NotBlank(message = "{notblank.tipo-despesa.nome}")
    @Column(length = 60, nullable = false)
    private String nome;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final TipoDespesa other = (TipoDespesa) obj;
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
