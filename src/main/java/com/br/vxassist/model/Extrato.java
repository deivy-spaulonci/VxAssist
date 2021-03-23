package com.br.vxassist.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
