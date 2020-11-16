package com.br.vxassist.model;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Audited
@AuditTable(value="extrato_aud")
@Entity
@Table(name = "EXTRATO")
public class Extrato implements Serializable {

    @Id
    @TableGenerator(name = "EXTRATO_IDS",
            table = "tabela_de_ids",
            pkColumnName = "tabela",
            pkColumnValue = "extrato_id",
            allocationSize = 1,
            initialValue = 1194,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EXTRATO_IDS")
    private Long id;

    @NotNull(message = "{notnull.extrato.data}")
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA", nullable = false)
    private Date data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "EXTRATO_DESCRICAO_ID")
    private ExtratoDescricao descricao;

    @Column(length = 255, nullable = true)
    private String documento;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal movimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ExtratoDescricao getDescricao() {
        return descricao;
    }

    public void setDescricao(ExtratoDescricao descricao) {
        this.descricao = descricao;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public BigDecimal getMovimento() {
        return movimento;
    }

    public void setMovimento(BigDecimal movimento) {
        this.movimento = movimento;
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
        Extrato other = (Extrato) obj;
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
