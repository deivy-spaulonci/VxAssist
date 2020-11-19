package com.br.vxassist.model;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//@Audited
//@AuditTable(value="extrato_descricao_aud")
@Entity
@Table(name = "EXTRATO_DESCRICAO")
public class ExtratoDescricao implements Serializable {

    @Id
    @TableGenerator(name = "EXTRATO_DESCRICAO_IDS",
            table = "tabela_de_ids",
            pkColumnName = "tabela",
            pkColumnValue = "extrato_descricao_id",
            allocationSize = 1,
            initialValue = 245,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EXTRATO_DESCRICAO_IDS")
    private Long id;

    @Size(min = 3, message = "{size.extrato-descricao.nome}")
    @NotBlank(message = "{notblank.extrato-descricao.nome}")
    @Column(length = 255, nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        ExtratoDescricao other = (ExtratoDescricao) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}
