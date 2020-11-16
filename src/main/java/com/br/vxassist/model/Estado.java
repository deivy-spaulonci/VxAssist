package com.br.vxassist.model;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Audited
@AuditTable(value="estado_aud")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

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
